package exercises;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import classes.Author;
import globalThings.SAXManager;
import globalThings.XMLDOMManager;

public class DiscografiaMain {
	public static final String FILE = "files/DiscografiaXML.xml";

	public static final String SEPARATION = "-----------------------------------------";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

		exercise1and2();
		exercise3();
		
		System.in.close();
	}

	private static void exercise1and2() throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = factory.newDocumentBuilder();

		File authors = new File(FILE);

		Document doc = builder.parse(authors);

		Node rootNode = doc.getFirstChild();
		
		List<Author> authorsList = XMLDOMManager.constructAuthorsList(rootNode);

		System.out.println("Ejercicio 1: \n" + SEPARATION);
		for (Author author : authorsList) {
			System.out.println(author + SEPARATION);
		}
		
		System.out.println("\nParte getElementsByTagName: \n");
		XMLDOMManager.searchBy(rootNode);

		System.out.println("\nEjercicio 2: \n" + SEPARATION);
		XMLDOMManager.insertAlbum(doc, authorsList);
		
		System.out.println("\nParte generar archivo ");		
		XMLDOMManager.createResumedXML(authorsList);
	}

	private static void exercise3() throws ParserConfigurationException, SAXException, IOException {
		System.out.println("\n\nEjercicio 3: \n" + SEPARATION);
		
		SAXParserFactory factory = SAXParserFactory.newInstance();

		SAXParser SAXParser = factory.newSAXParser();

		SAXParser.parse(FILE, new SAXManager());
	}

}
