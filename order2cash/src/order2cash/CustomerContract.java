package order2cash;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface CustomerContract {

	@WebMethod
	@Oneway
	void fillCart(String id, DataHandler productsData);
	
	@WebMethod
	@Oneway
	void createSalesOrder(String id, DataHandler cartData);
	
	@WebMethod
	@Oneway
	void createPaymentRequest(String id, DataHandler invoiceData);
	
	@WebMethod
	@Oneway
	void paymentNotification(String id, DataHandler paymentResponseData);
	
	@WebMethod
	@Oneway
	void onShipment(String id, DataHandler shipmentData);
}
