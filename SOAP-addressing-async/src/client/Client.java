package client;
import java.net.URL;
import java.util.Scanner;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;
import javax.xml.ws.Service;
import javax.xml.ws.soap.AddressingFeature;
import javax.xml.ws.soap.MTOM;

import com.sun.xml.internal.ws.api.addressing.OneWayFeature;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import common.ServerContract;

@SuppressWarnings("restriction")
@MTOM
public class Client {

	public static void main(String[] args) throws Exception {               
        EndpointReference reference =
        		Endpoint.publish("http://localhost:9090/clientService",
        				new ClientService())
        		.getEndpointReference();
        Service service = Service.create(
        		new URL("http://localhost:8080/serverService?wsdl"),
        		new QName("http://www.axway.com", "ServerService"));
        ServerContract proxy = service.getPort(
        		new QName("http://www.axway.com", "ServerPort"),
        		ServerContract.class,
                new AddressingFeature(),
                new OneWayFeature(true, new WSEndpointReference(reference)));  
        System.out.println("Enter a transformation name and file or 'exit' to stop.");
        try (Scanner scanner = new Scanner(System.in)) {
			while (true) {
				if (scanner.hasNextLine()) {
					String command = scanner.nextLine();
					if ("exit".equalsIgnoreCase(command)) {
						System.exit(0);
					} else {
						String[] arguments = command.split("\\s+");
						if (arguments.length == 2) {
							DataHandler handler = new DataHandler(
											new FileDataSource(ClientService.FOLDER + arguments[1]));
							proxy.transform(arguments[0], handler);
						}
					}
				}
			}
		}
    }

}
