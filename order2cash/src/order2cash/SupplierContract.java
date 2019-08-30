package order2cash;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SupplierContract {

	@WebMethod
	@Oneway
	void fillProducts();
	
	
	@WebMethod
	@Oneway
	void createInvoice(String id, DataHandler orderData);
	
	
	@WebMethod
	@Oneway
	void prepareInWarehouse(String id, DataHandler orderData);
	
}
