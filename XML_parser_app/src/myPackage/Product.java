package myPackage;

import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

/**
 * This is the class that maps our products from
 * XML files.
 * 
 * @author Grajdan Ioan Alexandru 
 *
 */
public class Product {
    private Node productElement;
    private LocalDateTime createdDate;
    private Double price;
    private String supplier;
    private String orderId;

    public Node getProductElement() {
		return productElement;
	}

	public void setProductElement(Node productElement) {
		this.productElement = productElement;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

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
