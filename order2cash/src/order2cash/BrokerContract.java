package order2cash;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BrokerContract {
	
	@WebMethod
	@Oneway
	void registerCustomer();
	
	@WebMethod
	@Oneway
	void registerSupplier();
	
	@WebMethod
	@Oneway
	void registerBank();

	@WebMethod
	@Oneway
	void start();
	
	@WebMethod
	@Oneway
	void onFillCart(String id, DataHandler cartData);
	
	@WebMethod
	@Oneway
	void onFillProducts(DataHandler productsData);
	
	@WebMethod
	@Oneway
	void onCreateSalesOrder(String id, DataHandler orderData);
	
	@WebMethod
	@Oneway
	void onCreateInvoice(String id, DataHandler invoiceData);
	
	@WebMethod
	@Oneway
	void onCreatePaymentRequest(String id, DataHandler paymentRequestData);
	
	@WebMethod
	@Oneway
	void onPaymentAccepted(String id, DataHandler paymentResponseData);
	
	@WebMethod
	@Oneway
	void onPaymentRejected(String id, DataHandler paymentResponseData);
	
	@WebMethod
	@Oneway
	void onPrepareInWareHouse(String id, DataHandler warehouseData);
	
	@WebMethod
	@Oneway
	void onShipment(String id, DataHandler shipmentData);
}
