package myPackage;

import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Product {
    public Node productElement;
    public LocalDateTime createdDate;
    public Double price;
    public String supplier;
    public String orderId;

    public Product(Node xmlElement, Node order) {
        this.productElement = xmlElement;
        this.supplier = ParseUtils.getNodeValue("supplier", productElement.getChildNodes());
        this.createdDate = LocalDateTime.parse(order.getAttributes().getNamedItem("created").getNodeValue());
        this.price = Double.parseDouble(ParseUtils.getNodeValue("price", productElement.getChildNodes()));
        this.orderId = order.getAttributes().getNamedItem("ID").getNodeValue();
    }
    
    public String toString() {
    	
    	return "Xml element : " + productElement + "\n" + "supplier : " + supplier + "\n" 
    			+ "Date : " + createdDate + "\n" + "price : " + price + "\n" 
    			+ "ID : " + orderId;
    }

}
