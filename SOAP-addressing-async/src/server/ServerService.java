package server;

import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlMimeType;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.soap.Addressing;
import javax.xml.ws.soap.MTOM;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;
import com.sun.xml.internal.ws.addressing.WsaPropertyBag;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

import common.ClientContract;
import common.ServerContract;

@SuppressWarnings("restriction")
@MTOM
@Addressing(required = true)
@WebService(
		serviceName = "ServerService",
		targetNamespace = "http://www.axway.com",
		portName = "ServerPort",
		endpointInterface = "common.ServerContract"
)
public class ServerService implements ServerContract {
	
	private static final String FOLDER = "data/server/";
	
	@Resource
	private WebServiceContext context;

	@Override
	public void transform(String mapper, @XmlMimeType("application/octet-stream") DataHandler handler) {
		try {
			String input = UUID.randomUUID().toString();
			String output = UUID.randomUUID().toString();
			handler.writeTo(new FileOutputStream(FOLDER + input));
			DocumentTransformer.transform(FOLDER + mapper, FOLDER + input, FOLDER + output);
			getReplyToEndpointReference()
				.getPort(ClientContract.class)
				.onTransform(new DataHandler(new FileDataSource(FOLDER + output)));
			Files.delete(Paths.get(FOLDER + input));
			Files.delete(Paths.get(FOLDER + output));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private W3CEndpointReference getReplyToEndpointReference() {
		WSEndpointReference reference = (WSEndpointReference) context.getMessageContext()
				.get(WsaPropertyBag.WSA_REPLYTO_FROM_REQUEST);
		
    	return new W3CEndpointReferenceBuilder()
    			.serviceName(new QName("http://www.axway.com", "ClientService"))
    			.address(reference.getAddress())
    			.wsdlDocumentLocation(reference.getAddress() + "?wsdl")
    			.build();
	}
}
