package demo;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

public class Utils {

	public static boolean validate(InputStream xml,
			InputStream xsd) {
		try {
			SchemaFactory factory =
					SchemaFactory.newInstance(
							XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new StreamSource(xsd));
			Validator validator = schema.newValidator();
			validator.validate(new StreamSource(xml));
			return true;
		} catch (SAXException | IOException e) {
			return false;
		}
	}
	
	
	public static OutputStream transform(InputStream xml,
			InputStream xsl,
			Map<String, Object> parameters)
					throws TransformerException {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Transformer transformer = TransformerFactory
				.newInstance()
				.newTransformer(new StreamSource(xsl));
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty(OutputKeys.METHOD, "xml");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
		if (parameters != null) {
			parameters.entrySet().forEach(entry ->
				transformer.setParameter(entry.getKey(), entry.getValue()));
		}
		transformer.transform(new StreamSource(xml), new StreamResult(stream));
		return stream;
	}
	
	public static OutputStream transform(InputStream xml,
			InputStream xsl)
					throws TransformerException {
		return transform(xml, xsl, null);
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		boolean valid = validate(new FileInputStream("data/store.xml"),
				new FileInputStream("data/store.xsd"));
		System.out.println(valid);
	}
}
