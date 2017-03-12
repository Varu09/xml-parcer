package myPackage;

import java.io.File;
import java.util.*;

import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;


import org.w3c.dom.Node;
import org.w3c.dom.NodeList;



public class MyDOMParcer {
	public static void main(String[] args) {

		try {
			File inputFile = new File("inputs/orders23.xml");
			String fileName = inputFile.getName();
			String digits = fileName.substring(fileName.length() - 6,fileName.length() - 4);
			//System.out.println(digits);
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(inputFile);
	

			//System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
			HashMap<String, List<Product>> productMap = new HashMap<>();
			NodeList orders = doc.getElementsByTagName("order");
			
			for(int i = 0; i < orders.getLength(); i++) {
				Node order = orders.item(i);
				NodeList productsElem = order.getChildNodes();
				
				for(int j = 0; j < productsElem.getLength(); j++) {
					Node productElement = productsElem.item(j);
					
					if(productElement.getNodeType() == Node.ELEMENT_NODE) {
						Product product = new Product(productElement, order);
						if (productMap.containsKey(product.supplier)) {
							productMap.get(product.supplier).add(product);
						} else {
							productMap.put(product.supplier, new ArrayList<>());
							productMap.get(product.supplier).add(product);
						}
						
					}
				}
				
				
			}
			
			productMap.entrySet().forEach(productList -> {
				sortProducts(productList.getValue());
				WriteXmlFile writeXmlFile = new WriteXmlFile(productList.getKey() + digits + ".xml");
				writeXmlFile.createFile(productList.getValue());
				});
			
			//System.out.println(productMap.size());
			//System.out.println(productMap.get("Apple").size());
			//productMap.get("Apple").forEach(System.out::println);					
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sortProducts(List<Product> products) {
		Collections.sort(products, new Comparator<Product>(){
			
			public int compare(Product p1, Product p2){
				int comparator = p2.createdDate.compareTo(p1.createdDate);
				return comparator != 0 ? comparator : p1.price.compareTo(p1.price);
			}
		});
	}
}