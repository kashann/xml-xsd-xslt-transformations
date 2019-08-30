package order2cash;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

@WebService(
	serviceName = Constants.BANK_SERVICE,
	targetNamespace = Constants.TARGET_NAMESPACE,
	portName = Constants.BANK_PORT,
	endpointInterface = "order2cash.BankContract"
)
@MTOM
@Addressing
public class BankService implements BankContract, Constants {
	
	@Resource
	private WebServiceContext context;

	@Override
	public void processPayment(String id, DataHandler paymentRequestData) {
		// TODO Auto-generated method stub

	}

}
