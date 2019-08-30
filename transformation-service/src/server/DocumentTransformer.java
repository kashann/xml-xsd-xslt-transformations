package server;

import java.io.File;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class DocumentTransformer {

	public static void transform(String mapper,
			String input,
			String output) throws TransformerException {
		Source data = new StreamSource(new File(input));
		StreamSource xsl = new StreamSource(new File(mapper));
		TransformerFactory factory = TransformerFactory.newInstance();
		Transformer transformer = factory.newTransformer(xsl);
		transformer.transform(data, new StreamResult(new File(output)));
	}
	
	public static void main(String[] args) throws TransformerException {
		transform("data/transformation.xsl",
				"data/input.xml",
				"data/output.xml");
	}
}
