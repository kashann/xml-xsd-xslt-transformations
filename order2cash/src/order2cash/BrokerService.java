package order2cash;

import static order2cash.Constants.BANK_SERVICE;
import static order2cash.Constants.CUSTOMER_SERVICE;
import static order2cash.Constants.SUPPLIER_SERVICE;
import static order2cash.Constants.TARGET_NAMESPACE;
import static order2cash.Utils.getReplyToEndpointReference;
import static order2cash.Utils.readFile;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.soap.MTOM;

import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

@WebService(
	serviceName = Constants.BROKER_SERVICE,
	targetNamespace = Constants.TARGET_NAMESPACE,
	portName = Constants.BROKER_PORT,
	endpointInterface = "order2cash.BrokerContract"
)
@MTOM
@Addressing
public class BrokerService implements BrokerContract {
	
	private Map<String, Object> proxies =
			new HashMap<String, Object>();
	
	@Resource
	private WebServiceContext context;

	private EndpointReference reference;
	
	@Override
	public void registerCustomer() {
		proxies.put(CUSTOMER_SERVICE,
				getReplyToEndpointReference(context,
				TARGET_NAMESPACE, CUSTOMER_SERVICE)
				.getPort(CustomerContract.class,
						new AddressingFeature(),
						new OneWayFeature(true,
								new WSEndpointReference(reference))));
	}
	
	@Override
	public void registerSupplier() {
		proxies.put(SUPPLIER_SERVICE,
				getReplyToEndpointReference(context,
				TARGET_NAMESPACE, SUPPLIER_SERVICE)
				.getPort(SupplierContract.class,
						new AddressingFeature(),
		                new OneWayFeature(true,
		                		new WSEndpointReference(reference))));
	}
	
	@Override
	public void registerBank() {
		proxies.put(BANK_SERVICE,
				getReplyToEndpointReference(context,
				TARGET_NAMESPACE, BANK_SERVICE)
				.getPort(BankContract.class,
						new AddressingFeature(),
		                new OneWayFeature(true,
		                		new WSEndpointReference(reference))));
	}

	@Override
	public void start() {
		supplier().fillProducts();
	}

	@Override
	public void onFillCart(String id, DataHandler cartData) {
		customer().createSalesOrder(id, cartData);
	}
	
	@Override
	public void onFillProducts(DataHandler productsData) {
		String id = UUID.randomUUID().toString();
		customer().fillCart(id, productsData);
	}

	@Override
	public void onCreateSalesOrder(String id, DataHandler orderData) {
		supplier().createInvoice(id, orderData);
	}

	@Override
	public void onCreateInvoice(String id, DataHandler invoiceData) {
		customer().createPaymentRequest(id, invoiceData);
	}

	@Override
	public void onCreatePaymentRequest(String id, DataHandler paymentRequestData) {
		bank().processPayment(id, paymentRequestData);
	}

	@Override
	public void onPaymentAccepted(String id, DataHandler paymentResponseData) {
		customer().paymentNotification(id, paymentResponseData);
		supplier().prepareInWarehouse(id, readFile("order.xml"));
	}

	@Override
	public void onPaymentRejected(String id, DataHandler paymentResponseData) {
		customer().paymentNotification(id, paymentResponseData);
	}

	@Override
	public void onPrepareInWareHouse(String id, DataHandler warehouseData) {

	}

	@Override
	public void onShipment(String id, DataHandler shipmentData) {
		customer().onShipment(id, shipmentData);
	}
	
	private SupplierContract supplier() {
		return (SupplierContract) proxies.get(SUPPLIER_SERVICE);
	}
	
	private CustomerContract customer() {
		return (CustomerContract) proxies.get(CUSTOMER_SERVICE);
	}

	private BankContract bank() {
		return (BankContract) proxies.get(BANK_SERVICE);
	}

	public void setReference(EndpointReference reference) {
		this.reference = reference;
	}
}
