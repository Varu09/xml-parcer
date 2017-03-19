author: Grajdan Ioan Alexandru

This application contains 4 classes implemented to solve the XML parsing problem.

1)MyDOMParser
  Is the main class of the application and has 2 methods:
  	- sortProducts which is used, as it is called, to sort the product after the criterion stated.
  	- main method which loops through all files in the input directory and uses the w3c DOM parser
  	to extract the information needed. That information is placed into a HashMap and after that, it 
 	is sorted with previous function mentioned.


2) ParseUtils
This class contains simple methods for parsing XML. It helps me to get through some issues i have encounter
like whitespaces which generates #text nodes.

3) WriteXmlFile
This is the method that generates XML files.
The core function is createFile which receives a product list. In the first part the function loops on all
products and for each of them it clones the product element and it replaces the supplier element, from the 
original XML, with order ID. The second part it simply writes the content saved into XML file.
It has also a contructor to create the root elements of XML files.

4) Product
This is the class that maps our products from XML files.
It contains all the description elements according to the input files.