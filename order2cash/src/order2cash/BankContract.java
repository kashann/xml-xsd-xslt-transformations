package order2cash;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface BankContract {

	@WebMethod
	@Oneway
	void processPayment(String id, DataHandler paymentRequestData);
	
}
