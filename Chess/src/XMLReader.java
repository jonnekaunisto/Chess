import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import org.xml.sax.*;
import org.w3c.dom.*;


//for saving game state, not finished
//from https://www.mkyong.com/java/how-to-read-xml-file-in-java-dom-parser/
public class XMLReader {
	
	public static void main(String argv[]) {
	    try {

		File fXmlFile = new File("src/Chess_Pieces/Locations.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(fXmlFile);
		
		doc.getDocumentElement().normalize();
		
		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		

		NodeList nList = doc.getElementsByTagName("piece");

		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);

			System.out.println("\nCurrent Element :" + nNode.getNodeName());
			System.out.println(nNode.getNodeType());

			if (nNode.getNodeType() == Node.ELEMENT_NODE) {

				Element eElement = (Element) nNode;

				System.out.println("Piece : " + eElement.getAttribute("id"));
				System.out.println("Side : " + eElement.getElementsByTagName("side").item(0).getTextContent());
				System.out.println("FirstMoce : " + eElement.getElementsByTagName("firstmove").item(0).getTextContent());
				System.out.println("EnPassant : " + eElement.getElementsByTagName("enpassant").item(0).getTextContent());
				System.out.println("Location : " + eElement.getElementsByTagName("location").item(0).getTextContent());
				eElement.getElementsByTagName("location").item(0).setTextContent("hi");
				

			}
		}
		




	}
	    catch(Exception e){
	    	System.out.println(e);
	    }
	}
}
	