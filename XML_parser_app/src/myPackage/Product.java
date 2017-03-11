package myPackage;

import org.w3c.dom.Node;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.Locale;

public class Product {
    Node xmlElement;
    LocalDateTime createdDate;
    Double price;
    String supplier;

    public Product(Node xmlElement, Node order) {
        this.xmlElement = xmlElement;
        this.supplier = xmlElement.getLastChild().getNodeValue();
        this.createdDate = LocalDateTime.parse(order.getAttributes().getNamedItem("created").getNodeValue());
        this.price = Double.parseDouble(xmlElement.getChildNodes().item(5).getFirstChild().getNodeValue());
    }

}