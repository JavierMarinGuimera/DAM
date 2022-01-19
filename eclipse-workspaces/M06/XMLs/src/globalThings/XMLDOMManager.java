package globalThings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import classes.Contacto;
import classes.Author;
import exercises.DiscografiaMain;

public class XMLDOMManager {
	private static final String OUTPUT_XML_FILE = "files/DiscografiaV2.xml";

	public static List<Author> constructAuthorsList(Node rootNode) {
		List<Author> authorsList = new ArrayList<Author>();

		NodeList authors = rootNode.getChildNodes();

		// FOR that will contain EVERY AUTHOR.
		for (int i = 0; i < authors.getLength(); i++) {
			if (authors.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String authorType = authors.item(i).getAttributes().getNamedItem("tipus").getNodeValue();
				String authorMembers = (authors.item(i).getAttributes().getNamedItem("num_components") != null
						? authors.item(i).getAttributes().getNamedItem("num_components").getNodeValue()
						: "");
				;
				String authorCountry = "";
				String authorName = "";

				List<Contacto> authorAlbums = new ArrayList<>();
				NodeList authorChilds = authors.item(i).getChildNodes();

				// FOR that will contain EVERY NODE under the AUTHOR
				for (int j = 0; j < authorChilds.getLength(); j++) {
					Node node = authorChilds.item(j);
					if (node.getNodeType() == Node.ELEMENT_NODE) {
						if (node.getNodeName().equals("Nom")) {
							authorCountry = node.getAttributes().getNamedItem("pais").toString();
							authorName = node.getTextContent();
						} else if (node.getNodeName().equals("Album")) {
							authorAlbums
									.add(new Contacto(node.getAttributes().getNamedItem("data_publicacio").getNodeValue(),
											node.getTextContent()));
						}
					}
				}

				authorsList.add(new Author(authorType, authorMembers, authorCountry, authorName, authorAlbums));
			}
		}

		return authorsList;
	}

	public static void searchBy(Node rootNode) {
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
			searchInAuthors(rootNode, scanner, "Fecha de publicación");
			break;

		case 2:
			searchInAuthors(rootNode, scanner, "Nombre");
			break;

		default:
			System.out.println("Algo ha ido mal...");
			break;
		}
	}

	public static void searchInAuthors(Node rootNode, Scanner scanner, String atributo) {
		System.out.println("Vamos a buscar por " + atributo.toLowerCase() + " del autor.");

		Element rootElement = (Element) rootNode;
		NodeList resultAttributeList = atributo.equals("Nombre") ? rootElement.getElementsByTagName("Nombre")
				: rootElement.getElementsByTagName("Album");

		System.out.println("Qué " + atributo.toLowerCase()
				+ (atributo.equals("Fecha de publicación") ? " ('1985' por ejemplo)" : "") + " quieres buscar?");
		String resultAttribute = scanner.nextLine();

		for (int i = 0; i < resultAttributeList.getLength(); i++) {
			Boolean hasFecha = resultAttribute.toLowerCase().equals(resultAttributeList.item(i).getAttributes()
					.getNamedItem("data_publicacio").getNodeValue().toString().toLowerCase());
			Boolean hasAlbum = resultAttribute.toLowerCase().equals(resultAttributeList.item(i).getTextContent().toLowerCase());

			if (hasFecha || hasAlbum) {
				System.out.println("El " + atributo.toLowerCase() + " se encuentra en el archivo.");

				System.out.println("Este es el album que hemos encontrado del artista: ");
				System.out.println(resultAttributeList.item(i).getAttributes().getNamedItem("data_publicacio")
						.getNodeValue().toString());
				System.out.println(resultAttributeList.item(i).getTextContent());

				int option;
				while (true) {
					System.out.println("Quieres modificar el " + atributo.toLowerCase() + "?");
					System.out.println("0: Salir del programa.");
					System.out.println("1: Si, quiero modificar el " + atributo.toLowerCase() + ".");
					System.out.println("2: No, quiero dejar el " + atributo.toLowerCase() + " tal cual.");

					try {
						option = Integer.parseInt(scanner.nextLine());
						if (option == 0) {
							break;
						}

						if (option == 1) {
							System.out.println("Cuál quieres que sea el nuevo " + atributo.toLowerCase() + "?");
							String newValue = scanner.nextLine();

							System.out.println(atributo + " anterior: " + resultAttributeList.item(i).getTextContent());

							resultAttributeList.item(i).setTextContent(newValue);

							System.out.println(atributo + " nuevo: " + resultAttributeList.item(i).getTextContent());

							System.out.println("Autor actualizado con éxito!");
						} else if (option == 1) {
							System.out.println("El " + atributo.toLowerCase() + " se ha quedado como estaba.");
						} else {
							throw new NumberFormatException();
						}
					} catch (Exception e) {
						System.out.println("Opción incorrecta!");
					}

				}

			}
		}

		System.out.println("Ya no hay más álbumes.");
	}

	public static void insertAlbum(Document doc, List<Author> authorsList) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Escribe el nombre del autor");
		String option = scanner.nextLine().trim();
		
		Boolean foundAuthor = false;

		NodeList elements = doc.getElementsByTagName("Nom");

		for (int i = 0; i < elements.getLength(); i++) {
			if (elements.item(i).getTextContent().equals(option) && !foundAuthor) {
				String fechaPublicacion;
				String nombreAlbum;

				while (true) {
					System.out.println("Introduce fecha de publicación del álbum:");
					fechaPublicacion = scanner.nextLine().trim();

					System.out.println("Introduce nombre del álbum:");
					nombreAlbum = scanner.nextLine().trim();

					if (isNumeric(fechaPublicacion) && !isNumeric(nombreAlbum)) {
						break;
					} else {
						System.out.println(
								"¡Error! Debes introducir un número para la fecha de publicación y un texto para el nombre del album.");
					}
				}
				
				if (!fechaPublicacion.equals("") && !nombreAlbum.equals("")) {
					Element album = doc.createElement("Album");
					album.setAttribute("data_publicacio", fechaPublicacion);
					album.appendChild(doc.createTextNode(nombreAlbum));
					elements.item(i).getParentNode().appendChild(album);
					elements.item(i).getParentNode().appendChild(doc.createTextNode("\n"));
					authorsList.get(i).addAuthorAlbum(new Contacto(fechaPublicacion, nombreAlbum));
				}

				foundAuthor = true;
			}
		}
		
		if (!foundAuthor) {
			System.out.println("No se ha encontrado el autor.");
		} else {
			try {
				TransformerFactory transformerFactory = TransformerFactory.newInstance();
				Transformer transformer;
				transformer = transformerFactory.newTransformer();
				DOMSource source = new DOMSource(doc);
				
				StreamResult result = new StreamResult(new File(DiscografiaMain.FILE));
				transformer.transform(source, result);
				
				System.out.println("Album insertado con éxito!!");
			} catch (TransformerConfigurationException e) {
				e.printStackTrace();
			} catch (TransformerException e) {
				e.printStackTrace();
			}
		}
		
		scanner.close();
	}

	public static void createResumedXML(List<Author> authorsList) {
		new File(OUTPUT_XML_FILE).delete();

		Scanner scanner = new Scanner(System.in);

		try {
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

			// root elements
			Document doc = docBuilder.newDocument();

			Element rootElement = doc.createElement("Musica");
			doc.appendChild(rootElement);
			rootElement.appendChild(doc.createTextNode("\n"));

			for (Author author : authorsList) {
				for (Contacto album_to_xml : author.getAuthorAlbums()) {
					Element album = doc.createElement("Album");
					album.setAttribute("autor", author.getAuthorName());
					album.setAttribute("data_publicacio", album_to_xml.getDate());
					album.appendChild(doc.createTextNode(album_to_xml.getName()));
					rootElement.appendChild(album);
					rootElement.appendChild(doc.createTextNode("\n"));
				}
			}

			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(OUTPUT_XML_FILE));
			transformer.transform(source, result);

			System.out.println("Archivo creado con éxito!");

		} catch (

		ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}

		scanner.close();
	}

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
