package order2cash;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.xml.namespace.QName;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.wsaddressing.W3CEndpointReference;
import javax.xml.ws.wsaddressing.W3CEndpointReferenceBuilder;

import com.sun.xml.internal.ws.addressing.WsaPropertyBag;
import com.sun.xml.internal.ws.api.addressing.WSEndpointReference;

public final class Utils implements Constants {

	public static W3CEndpointReference getReplyToEndpointReference(
			WebServiceContext context,
			String targetNamespace,
			String serviceName) {
		WSEndpointReference reference =
				(WSEndpointReference) context.getMessageContext()
				.get(WsaPropertyBag.WSA_REPLYTO_FROM_REQUEST);
    	return new W3CEndpointReferenceBuilder()
    			.serviceName(new QName(targetNamespace, serviceName))
    			.address(reference.getAddress())
    			.wsdlDocumentLocation(reference.getAddress() + "?wsdl")
    			.build();
	}
	
	public static DataHandler readFile(String path) {
		return new DataHandler(new FileDataSource(path));
	}
	
	public static BrokerContract broker(WebServiceContext context) {
		return getReplyToEndpointReference(context,
				TARGET_NAMESPACE, BROKER_SERVICE)
				.getPort(BrokerContract.class);
	}
	
}
