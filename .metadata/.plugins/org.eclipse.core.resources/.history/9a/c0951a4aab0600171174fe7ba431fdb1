package myPackage;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Este doar un test sa vad cam cum as putea genera 
 * fisiere XML prin StAX 
 * @author Alex
 *
 */

public class MyStAXCreator {

	public static void main(String[] args) {
	
		try {
			StringWriter stringWriter = new StringWriter();

	        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();	
	        XMLStreamWriter xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(stringWriter);
			
	        xMLStreamWriter.writeStartDocument();
	        xMLStreamWriter.writeStartElement("products");
	        
	        xMLStreamWriter.writeStartElement("product");
	        
	        xMLStreamWriter.writeStartElement("description");
	        xMLStreamWriter.writeCharacters("Sony 54.6'' (Diag) Xbr Hx929 Internet Tv");
	        xMLStreamWriter.writeEndElement();
	        
	        xMLStreamWriter.writeStartElement("gtin");
	        xMLStreamWriter.writeCharacters("00027242816657");
	        xMLStreamWriter.writeEndElement();
	        
	        xMLStreamWriter.writeStartElement("price");
	        xMLStreamWriter.writeAttribute("currency", "USD");
	        xMLStreamWriter.writeCharacters("2999.99");
	        xMLStreamWriter.writeEndElement();
	        
	        xMLStreamWriter.writeStartElement("supplier");
	        xMLStreamWriter.writeCharacters("Sony");
	        xMLStreamWriter.writeEndElement();
	        
	        xMLStreamWriter.writeEndElement();
	        xMLStreamWriter.writeEndDocument();

	        xMLStreamWriter.flush();
	        xMLStreamWriter.close();

	        String xmlString = stringWriter.getBuffer().toString();

	        stringWriter.close();

	        System.out.println(xmlString);
	        
		  }	catch (XMLStreamException e) {
	         e.printStackTrace();
	      } catch (IOException e) {	         
	         e.printStackTrace();
	      }

	}

}
