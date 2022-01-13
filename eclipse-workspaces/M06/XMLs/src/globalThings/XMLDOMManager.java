package globalThings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import classes.Author;

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
							authorAlbums.put(node.getAttributes().getNamedItem("data_publicacio").getNodeValue(),
									node.getTextContent());
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

	public static void searchAlbum(Node rootNode, Scanner scanner, String atributo) {
		System.out.println("Vamos a buscar por " + atributo.toLowerCase() + " del autor.");

		Element rootElement = (Element) rootNode;
		NodeList resultAttributeList = rootElement.getElementsByTagName("Album");

		System.out.println("Qué " + atributo.toLowerCase()
				+ (atributo.equals("Fecha de publicación") ? " ('1985' por ejemplo)" : "") + " quieres buscar?");
		String resultAttribute = scanner.nextLine().trim();

		for (int i = 0; i < resultAttributeList.getLength(); i++) {		
			Boolean hasFecha = resultAttribute.equals(resultAttributeList.item(i).getAttributes().getNamedItem("data_publicacio").getNodeValue().toString());
			Boolean hasAlbum = resultAttribute.equals(resultAttributeList.item(i).getTextContent());
			
			if (hasFecha || hasAlbum) {
				System.out.println("El " + atributo.toLowerCase() + " se encuentra en el archivo.");
				
				System.out.println("Este es el album que hemos encontrado del artista: ");
				System.out.println(resultAttributeList.item(i).getAttributes().getNamedItem("data_publicacio").getNodeValue().toString());
				System.out.println(resultAttributeList.item(i).getTextContent());

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

							System.out.println("Autor actualizado con éxito!");
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
			}
		}

		System.out.println("El atributo '" + atributo.toLowerCase() + "' no se encuentra en el archivo.");
	}

	public static void insertAlbum(List<Author> authorsList) {
		new File(OUTPUT_XML_FILE).delete();
		
		Scanner scanner = new Scanner(System.in);
		
		try {
		    DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		    DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

		    //root elements
		    Document doc = docBuilder.newDocument();

		    Element rootElement = doc.createElement("Musica");
		    doc.appendChild(rootElement);

		    for (Author author : authorsList) {
		    	
		    	System.out.println("Quieres añadir un album a " + author.getAuthorName() + "? (Si/No)");
		    	String fechaPublicacion = "";
		    	String nombreAlbum = "";
		    	
		    	if (scanner.nextLine().trim().toLowerCase().equals("si")) {
		    		while (true) {
		    			System.out.println("Introduce fecha de publicación del álbum:");
			    		fechaPublicacion = scanner.nextLine().trim();
			    		
			    		System.out.println("Introduce nombre del álbum:");
			    		nombreAlbum = scanner.nextLine().trim();
			    		
			    		if (isNumeric(fechaPublicacion) && !isNumeric(nombreAlbum)) {
			    			break;
			    		} else {			    			
			    			System.out.println("¡Error! Debes introducir un número para la fecha de publicación y un texto para el nombre del album.");
			    		}    			
		    		}
		    	}
			    
			    for (Map.Entry<String, String> entry : author.getAuthorAlbums().entrySet()) {
			    	Element album = doc.createElement("Album");
			    	album.setAttribute("autor", author.getAuthorName());	
				    album.setAttribute("data_publicacio", entry.getKey());
				    album.appendChild(doc.createTextNode(entry.getValue()));
				    rootElement.appendChild(album);
				}
			    
			    if (!fechaPublicacion.equals("") && !author.getAuthorName().equals("") && !nombreAlbum.equals("")) {
			    	Element album = doc.createElement("Album");
			    	album.setAttribute("autor", author.getAuthorName());	
				    album.setAttribute("data_publicacio", fechaPublicacion);
				    album.appendChild(doc.createTextNode(nombreAlbum));
				    rootElement.appendChild(album);
			    }
			}

		    TransformerFactory transformerFactory =  TransformerFactory.newInstance();
		    Transformer transformer = transformerFactory.newTransformer();
		    DOMSource source = new DOMSource(doc);

		    StreamResult result =  new StreamResult(new File(OUTPUT_XML_FILE));
		    transformer.transform(source, result);

		    System.out.println("Archivo creado con éxito!");

		}catch(ParserConfigurationException pce){
		    pce.printStackTrace();
		}catch(TransformerException tfe){
		    tfe.printStackTrace();
		}
		
		scanner.close();
	}

	public static boolean isNumeric(String str) { 
		  try {  
		    Double.parseDouble(str);  
		    return true;
		  } catch(NumberFormatException e){  
		    return false;  
		  }  
		}
}
