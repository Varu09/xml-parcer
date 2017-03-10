package myPackage;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class UserHandler extends DefaultHandler {
	
	boolean bProduct = false;
	boolean bDescription = false;
	boolean bGtin = false;
	boolean bPrice = false;
	boolean bSupplier = false;
	
	@Override
	   public void startElement(String uri, 
	   String localName, String qName, Attributes attributes)
			throws SAXException {
		
		if (qName.equalsIgnoreCase("order")) {
			String orderTime = attributes.getValue("created");
			String orderId = attributes.getValue("ID");
			System.out.println("Order Created : " + orderTime +" \n"
					+ "ID : " + orderId);
		} else if(qName.equalsIgnoreCase("product")) {
			bProduct = true;
		} else if(qName.equalsIgnoreCase("description")) {
			bDescription = true;
		} else if(qName.equalsIgnoreCase("gtin")) {
			bGtin = true;
		} else if(qName.equalsIgnoreCase("price")) {
			bPrice = true;
		} else if(qName.equalsIgnoreCase("supplier")) {
			bSupplier = true;
		}
			
		
	}
	
	@Override
	   public void endElement(String uri, 
	   String localName, String qName) throws SAXException {
	      if (qName.equalsIgnoreCase("order")) {
	         System.out.println("End Element : " + qName + "\n");
	         System.out.println("------------------------------\n");
	      }
	   }
	
	@Override
	   public void characters(char ch[], 
	      int start, int length) throws SAXException {
		
		if(bProduct) {
			System.out.println("\nProduct : " 
						+ new String(ch, start, length));
			bProduct = false;
		} else if(bDescription) {
			System.out.println("Description : " 
					+ new String(ch, start, length));
			bDescription = false;
			
		} else if(bGtin) {
			System.out.println("Gtin : " 
					+ new String(ch, start, length));
			bGtin = false;
			
		} else if(bPrice) {
			System.out.println("Price : " 
					+ new String(ch, start, length));
			bPrice = false;
			
		} else if(bSupplier) {
			System.out.println("Supplier : " 
					+ new String(ch, start, length));
			bSupplier = false;
			
		}
	}
	
}