package exercises;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import globalThings.Libro;

public class Exercise1To3 {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = factory.newDocumentBuilder();

		File books = new File("files/LibrosXML.xml");
		
		System.out.println(books.length());
		
		Document doc = builder.parse(books);

		Node rootNode = doc.getFirstChild();

		List<Libro> booksList = constructBooksList(rootNode);
		
		for (Libro libro : booksList) {
			System.out.println(libro.getTitle());
		}

	}

	private static List<Libro> constructBooksList(Node rootNode) {
		List<Libro> booksList = new ArrayList<Libro>();
		
		NodeList books = rootNode.getChildNodes();
		
		List<String> publicateds = new ArrayList<String>();
		List<String> titles = new ArrayList<String>();
		List<String> authors = new ArrayList<String>();
		
		for (int i = 0; i < books.getLength(); i++) {
			if (books.item(i).getNodeType() == Node.ELEMENT_NODE) {
				publicateds.add(books.item(i).getAttributes().getNamedItem("publicado_en").getNodeValue());
				NodeList bookChilds = books.item(i).getChildNodes();
				for (int j = 0; j < bookChilds.getLength(); j++) {
					Node node = bookChilds.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("Titulo")) {
							titles.add(node.getTextContent());
						} else if (node.getNodeName().equals("Autor")) {
							authors.add(node.getTextContent());
						}
					}
	 			}
				System.out.println(authors.get(0));
//				booksList.add(new Libro(publicateds.get(i), titles.get(i), authors.get(i)));
			}
		}
		
		return booksList;
	}

	private static Libro constructBooksLis(Node currentNode) {
		List<String> bookData = new ArrayList<String>();
		bookData.add(currentNode.getAttributes().item(0).getNodeValue());

		NodeList data = currentNode.getChildNodes();

		for (int i = 0; i < data.getLength(); i++) {
			
			if (data.item(i).getNodeType() == Node.ELEMENT_NODE) {
				Element el = (Element) data.item(i);
				el.getElementsByTagName("");
				bookData.add(data.item(i).getNodeValue());
				System.out.println(data.item(i).getNodeName());
				System.out.println(data.item(i).getTextContent());
			}
			
		}
		
		return new Libro(bookData.get(0), bookData.get(1), bookData.get(2));

	}

}