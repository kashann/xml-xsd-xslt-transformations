package order2cash;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Service;
import javax.xml.ws.soap.AddressingFeature;
import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

public class SupplierServer implements Constants {

	public static void main(String[] args) throws MalformedURLException {
		try {
			EndpointReference reference =
        		Endpoint.publish("http://localhost:8282/supplierService",
        				new SupplierService())
        		.getEndpointReference();
		  Service service = Service.create(
        		new URL("http://localhost:8080/brokerService?wsdl"),
        		new QName(TARGET_NAMESPACE, BROKER_SERVICE));
		  BrokerContract broker = service.getPort(
        		new QName(TARGET_NAMESPACE, BROKER_PORT),
        		BrokerContract.class,
                new AddressingFeature(),
                new OneWayFeature(true, new WSEndpointReference(reference)));
		  broker.registerSupplier();
		  try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					if (scanner.hasNextLine()
							&& "exit".equalsIgnoreCase(
									scanner.nextLine())) {
						System.exit(0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
