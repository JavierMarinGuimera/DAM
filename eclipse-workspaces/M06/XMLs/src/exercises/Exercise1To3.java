package exercises;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

		Document doc = builder.parse(books);

		Node rootNode = doc.getFirstChild();

		List<Libro> booksList = constructBooksList(rootNode);

		System.out.println("Ejercicio 1: \n");
		System.out.println("Libros:");
		for (Libro libro : booksList) {
			System.out.println("Título: " + libro.getTitle());
			System.out.println("Autor: " + libro.getAuthor());
			System.out.println("Fecha de publicación: " + libro.getPublication() + "\n");
		}

		System.out.println("\nEjercicio 2:");
		searchBy(rootNode);

	}

	private static List<Libro> constructBooksList(Node rootNode) {
		List<Libro> booksList = new ArrayList<Libro>();

		NodeList books = rootNode.getChildNodes();

		for (int i = 0; i < books.getLength(); i++) {
			if (books.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String publication = "";
				String title = "";
				String author = "";
				publication = books.item(i).getAttributes().getNamedItem("publicado_en").getNodeValue();
				NodeList bookChilds = books.item(i).getChildNodes();

				for (int j = 0; j < bookChilds.getLength(); j++) {
					Node node = bookChilds.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("Titulo")) {
							title = node.getTextContent();
						} else if (node.getNodeName().equals("Autor")) {
							author = node.getTextContent();
						}
					}
				}

				booksList.add(new Libro(publication, title, author));
			}
		}

		return booksList;
	}

	private static void searchBy(Node rootNode) {
		Scanner scanner = new Scanner(System.in);

		int option;

		while (true) {
			System.out.println("Elige cómo quieres hacer la búsqueda");
			System.out.println("0: Salir del programa.");
			System.out.println("1: Buscar por título del libro.");
			System.out.println("2: Buscar por autor del libro.");

			try {
				option = Integer.parseInt(scanner.nextLine());
				if (option >= 0 && option <= 2) {
					break;
				}
			} catch (Exception e) {
				System.out.println("Debes introducir un valor válido. \n");
			}

		}

		switch (option) {
		case 0:
			System.out.println("Hasta otra!");
			break;

		case 1:
			searchBook(rootNode, scanner, "Titulo");
			break;

		case 2:
			searchBook(rootNode, scanner, "Autor");
			break;

		default:
			System.out.println("Algo ha ido mal...");
			break;
		}

		scanner.close();
	}

	private static void searchBook(Node rootNode, Scanner scanner, String atributo) {
		System.out.println("Vamos a buscar por " + atributo.toLowerCase() + " del libro.");

		Element rootElement = (Element) rootNode;
		NodeList titles = rootElement.getElementsByTagName(atributo);

		System.out.println("Qué " + atributo.toLowerCase() + " quieres buscar?");
		String userTitle = scanner.nextLine().trim();

		for (int i = 0; i < titles.getLength(); i++) {
			if (userTitle.equals(titles.item(i).getTextContent())) {
				System.out.println("El " + atributo.toLowerCase() + " se encuentra en el archivo.");
				
				int option;
				while (true) {
					System.out.println("Quieres modificar el " + atributo.toLowerCase() + "?");
					System.out.println("0: Si, quiero modificar el " + atributo.toLowerCase() + ".");
					System.out.println("1: No, quiero dejar el " + atributo.toLowerCase() + " tal cual.");
					
					try {
						option = Integer.parseInt(scanner.nextLine());
						if (option == 0) {
							System.out.println("Cuál quieres que sea el nuevo " + atributo.toLowerCase() + "?");
							String newValue = scanner.nextLine();
							
							System.out.println(atributo + " anterior: " + titles.item(i).getTextContent());
							
							titles.item(i).setTextContent(newValue);
							
							System.out.println(atributo + " nuevo: " + titles.item(i).getTextContent());
							
							System.out.println("Libro actualizado con éxito!");
						} else if (option == 1) {
							System.out.println("El " + atributo.toLowerCase() + " se ha quedado como estaba.");
						} else {
							throw new NumberFormatException();
						} 
						
						break;
					} catch (Exception e) {
						System.out.println("Opción incorrecta!");
					}
				}
				return;
			}
		}

		System.out.println("El " + atributo.toLowerCase() + " no se encuentra en el archivo.");
	}
}
