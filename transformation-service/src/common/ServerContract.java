package common;

import javax.activation.DataHandler;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface ServerContract {

	@WebMethod(action = "http://www.axway.com/transform/request")
	@Oneway
	void transform(String mapper, DataHandler handler);
}
