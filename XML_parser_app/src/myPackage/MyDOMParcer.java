package myPackage;

import java.io.File;
import java.util.*;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyDOMParcer {
	public static void main(String[] args) {

		try {
			File inputFile = new File("orders23.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);

			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			HashMap<String, List<Product>> productMap = new HashMap<>();
			NodeList orders = doc.getElementsByTagName("order");
			for(int i=0; i<orders.getLength(); i++) {
				Node order = orders.item(i);
				NodeList productsElem = order.getChildNodes();
				for(int j=0;j<productsElem.getLength();j++){
					Node productElement = productsElem.item(j);
					if(productElement.getNodeType() == Node.ELEMENT_NODE) {
						Product product = new Product(productElement, order);
						System.out.println(product.price);
						if (productMap.containsKey(product.supplier)) {
							productMap.get(product.supplier).add(product);
						} else {
							productMap.put(product.supplier, new ArrayList<>());
							productMap.get(product.supplier).add(product);
						}

						//products.add(product);
						// TODO
						/*

						 */
					}
				}
			}

			//sortProducts(products);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sortProducts(List<Product> products) {
		Collections.sort(products, new Comparator<Product>(){
			public int compare(Product p1, Product p2){
				int comparator = p1.createdDate.compareTo(p2.createdDate);
				return comparator != 0 ? comparator : p1.price.compareTo(p1.price);
			}
		});
	}
	//WriteXmlFile writeXmlFile = new WriteXmlFile();
	//writeXmlFile.createFile();
}