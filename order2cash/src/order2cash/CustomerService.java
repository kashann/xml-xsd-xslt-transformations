package order2cash;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

@WebService(
	serviceName = "CustomerService",
	targetNamespace = "http://axway.com",
	portName = "CustomerPort",
	endpointInterface = "order2cash.CustomerContract"
)
@MTOM
@Addressing
public class CustomerService implements CustomerContract {
	
	@Resource
	private WebServiceContext context;

	@Override
	public void fillCart(String id, DataHandler productsData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void createSalesOrder(String id, DataHandler cartData) {
		
	}
	
	@Override
	public void createPaymentRequest(String id, DataHandler invoiceData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void paymentNotification(String id, DataHandler paymentResponseData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onShipment(String id, DataHandler shipmentData) {
		// TODO Auto-generated method stub

	}

}
