package order2cash;

import java.util.Scanner;

import javax.xml.ws.Endpoint;
import javax.xml.ws.EndpointReference;

public class BrokerServer {

	public static void main(String[] args) {
		try {
			BrokerService broker = new BrokerService();
			EndpointReference reference =
	        		Endpoint.publish("http://localhost:8080/brokerService",
	        				broker)
	        		.getEndpointReference();
			broker.setReference(reference);
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
