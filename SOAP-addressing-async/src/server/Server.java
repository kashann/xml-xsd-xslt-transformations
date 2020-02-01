package server;

import java.util.Scanner;

import javax.xml.ws.Endpoint;

public class Server {

	public static void main(String[] args) {
		try {
			String endpoint = "http://localhost:8080/serverService";
			Endpoint.publish(endpoint, new ServerService());
			System.out.println("Server is listening on '" + endpoint + "', type 'exit' to stop it.");
			try (Scanner scanner = new Scanner(System.in)) {
				while (true) {
					if (scanner.hasNextLine() && "exit".equalsIgnoreCase(scanner.nextLine())) {
						System.exit(0);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
}
