package globalThings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import classes.Author;

public class XMLDOMManager {

	private static List<Author> constructAuthorsList(Node rootNode) {
		List<Author> authorsList = new ArrayList<Author>();

		NodeList authors = rootNode.getChildNodes();

		// FOR that will contain EVERY AUTHOR.
		for (int i = 0; i < authors.getLength(); i++) {
			if (authors.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String authorType = authors.item(i).getAttributes().getNamedItem("tipus").toString();
				String authorMembers = authors.item(i).getAttributes().getNamedItem("num_components").toString();
				String authorCountry = "";
				String authorName = "";

				Map<String, String> authorAlbums = new TreeMap<>();
				NodeList authorChilds = authors.item(i).getChildNodes();

				// FOR that will contain EVERY NODE under the AUTHOR
				for (int j = 0; j < authorChilds.getLength(); j++) {
					Node node = authorChilds.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("Nom")) {
							authorCountry = node.getAttributes().getNamedItem("pais").toString();
							authorName = node.getTextContent();
						} else if (node.getNodeName().equals("Album")) {
							authorAlbums.put(node.getAttributes().getNamedItem("data_publicacio").toString(),
									node.getTextContent());
						}
					}
				}

				authorsList.add(new Author(authorType, authorMembers, authorCountry, authorName, authorAlbums));
			}
		}

		return authorsList;
	}

	private static void searchBy(Node rootNode) {
		Scanner scanner = new Scanner(System.in);

		int option;

		while (true) {
			System.out.println("Elige cómo quieres hacer la búsqueda");
			System.out.println("0: Salir del programa.");
			System.out.println("1: Buscar por fecha publicacion del autor.");
			System.out.println("2: Buscar por nombre del album del autor.");

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
			searchAlbum(rootNode, scanner, "Fecha de publicación");
			break;

		case 2:
			searchAlbum(rootNode, scanner, "Nombre");
			break;

		default:
			System.out.println("Algo ha ido mal...");
			break;
		}

		scanner.close();
	}

	private static void searchAlbum(Node rootNode, Scanner scanner, String atributo) {
		System.out.println("Vamos a buscar por " + atributo.toLowerCase() + " del autor.");

		Element rootElement = (Element) rootNode;
		NodeList resultAttributeList = rootElement.getElementsByTagName("Album");
		
		System.out.println("Qué " + atributo.toLowerCase()
				+ (atributo.equals("Fecha de publicación") ? " ('1985' por ejemplo)" : "") + " quieres buscar?");
		String resultAttribute = scanner.nextLine().trim();

		for (int i = 0; i < resultAttributeList.getLength(); i++) {
			if (resultAttribute.equals(resultAttributeList.item(i).getTextContent())) {
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

							System.out.println(atributo + " anterior: " + resultAttributeList.item(i).getTextContent());

							resultAttributeList.item(i).setTextContent(newValue);

							System.out.println(atributo + " nuevo: " + resultAttributeList.item(i).getTextContent());

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
