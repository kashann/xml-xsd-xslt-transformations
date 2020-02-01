package client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;

import common.ClientContract;

@MTOM
@Addressing(required = true)
@WebService(
		serviceName = "ClientService",
		targetNamespace = "http://www.axway.com",
		endpointInterface = "common.ClientContract"
)
public class ClientService implements ClientContract {
	
	public static final String FOLDER = "data/client/";

	@Override
	public void onTransform(@XmlMimeType("application/octet-stream") DataHandler handler) {
		try {
			String output = UUID.randomUUID().toString();
			handler.writeTo(new FileOutputStream(FOLDER + output));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
