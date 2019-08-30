package order2cash;

import javax.activation.DataHandler;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

import static order2cash.Utils.*;

@WebService(
	serviceName = Constants.SUPPLIER_SERVICE,
	targetNamespace = Constants.TARGET_NAMESPACE,
	portName = Constants.SUPPLIER_PORT,
	endpointInterface = "order2cash.SupplierContract"
)
@MTOM
@Addressing
public class SupplierService implements SupplierContract, Constants {

	@Resource
	private WebServiceContext context;
	
	@Override
	public void fillProducts() {
		broker(context).onFillProducts(readFile("products.xml"));
	}

	@Override
	public void createInvoice(String id, DataHandler orderData) {
		// TODO Auto-generated method stub

	}

	@Override
	public void prepareInWarehouse(String id, DataHandler orderData) {
		// TODO Auto-generated method stub

	}
	


}
