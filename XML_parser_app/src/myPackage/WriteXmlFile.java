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

/**
 * This is the method that generates XML files.
 * The core function is createFile which receives a product list.
 * In the first part the function loops on all products and for 
 * each of them it clones the product element and it replaces
 * the supplier element, from the original XML, with order ID.
 * The second part it simply writes the content saved into XML
 * file.
 * 
 * @author Grajdan Ioan Alexandru 
 *
 */
public class WriteXmlFile {

   public DocumentBuilderFactory docFactory;
   public DocumentBuilder docBuilder;
   public String fileName;
   public Document doc;
   public Element rootElement;

    public WriteXmlFile(String fileName) {
        try {
        docFactory = DocumentBuilderFactory.newInstance();
        docBuilder = docFactory.newDocumentBuilder();
        
            // root elements
            doc = docBuilder.newDocument();
            rootElement = doc.createElement("products");
            doc.appendChild(rootElement);
            this.fileName = fileName;

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
	
	

    public void createFile (List<Product> products) {


            try {           	
            	
            for(Product product : products){
            	Node productElement =  product.getProductElement().cloneNode(true);
            	doc.adoptNode(productElement);
              	Node supplier = ParseUtils.getNode("supplier", productElement.getChildNodes());
            	Element orderId = doc.createElement("orderid");
            	orderId.appendChild(doc.createTextNode(product.getOrderId()));
            	productElement.replaceChild(orderId, supplier);
            	rootElement.appendChild(productElement);
            }


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            
            // saves the file in the directory 
            StreamResult result = new StreamResult(new File("outputs/" + fileName));
            
            transformer.transform(source, result);

            System.out.println("File saved!");

            } catch (TransformerException e) {
                e.printStackTrace();
            }
    }
}
