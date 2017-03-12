package myPackage;

import java.io.File;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class WriteXmlFile {

    DocumentBuilderFactory docFactory;
    DocumentBuilder docBuilder;
    String fileName;
    Document doc;
    Element rootElement;

    public WriteXmlFile(String fileName) {
        try {
        docFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docFactory.newDocumentBuilder();
            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("products");
            doc.appendChild(rootElement);
            this.fileName = fileName;

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        }
    }
	
	

    public void createFile (List<Product> products) {


            try {           	
            	
            for(Product product : products){
            	Node productElement =  product.productElement.cloneNode(true);
            	doc.adoptNode(productElement);
              	Node supplier = ParseUtils.getNode("supplier", productElement.getChildNodes());
            	Element orderId = doc.createElement("orderid");
            	orderId.appendChild(doc.createTextNode(product.orderId));
            	productElement.replaceChild(orderId, supplier);
            	rootElement.appendChild(productElement);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("outputs/" + fileName));
            

            //StreamResult result = new StreamResult(new File("file.xml"));
            // Output to console for testing
            // StreamResult result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

            } catch (TransformerException e) {
                e.printStackTrace();
            }
    }
}
