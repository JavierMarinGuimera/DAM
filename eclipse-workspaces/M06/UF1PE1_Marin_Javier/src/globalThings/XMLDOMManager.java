package globalThings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

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
import classes.Departamento;
import main.AgendaMain;

public class XMLDOMManager {
	public static List<Contacto> constructContactosList(Node rootNode) {
		List<Contacto> ContactosList = new ArrayList<Contacto>();

		NodeList contactos = rootNode.getChildNodes();

		// FOR that will contain EVERY Contacto.
		for (int i = 0; i < contactos.getLength(); i++) {
			if (contactos.item(i).getNodeType() == Node.ELEMENT_NODE) {
				String id = "";
				String nombre = "";
				String apellidos = "";
				Departamento departamento = null;
				HashMap<String, String> telefonos = new HashMap<String, String>();
				HashMap<String, String> correos = new HashMap<String, String>();

				NodeList contactoChilds = contactos.item(i).getChildNodes();

				// FOR that will contain EVERY NODE under the Contacto
				for (int j = 0; j < contactoChilds.getLength(); j++) {
					Node nodoContacto = contactoChilds.item(j);
					if (nodoContacto.getNodeType() == Node.ELEMENT_NODE) {
						switch (nodoContacto.getNodeName()) {
						case "Identificacio":
							id = nodoContacto.getAttributes().getNamedItem("nif").toString();
							NodeList atributosPersonales = nodoContacto.getChildNodes();

							for (int k = 0; k < atributosPersonales.getLength(); k++) {
								Node nodoAtributoPersonal = atributosPersonales.item(k);
								if (nodoAtributoPersonal.getNodeType() == Node.ELEMENT_NODE) {
									if (nodoAtributoPersonal.getNodeName().equals("Nom")) {
										nombre = nodoAtributoPersonal.getTextContent();
									} else {
										apellidos = nodoAtributoPersonal.getTextContent();
									}
								}
							}
							break;

						case "Departament":
							String nombreDepartamento = "";
							String asignaturaDepartamento = "";

							NodeList atributosDepartamento = nodoContacto.getChildNodes();

							for (int k = 0; k < atributosDepartamento.getLength(); k++) {
								Node nodoAtributoDepartamento = atributosDepartamento.item(k);
								if (nodoAtributoDepartamento.getNodeType() == Node.ELEMENT_NODE) {
									if (nodoAtributoDepartamento.getNodeName().equals("Nom")) {
										nombreDepartamento = nodoAtributoDepartamento.getTextContent();
									} else {
										asignaturaDepartamento = nodoAtributoDepartamento.getTextContent();
									}
								}
							}

							departamento = new Departamento(nombreDepartamento, asignaturaDepartamento);
							break;

						case "Telefon":
							String tipoTelefono = nodoContacto.getAttributes().getNamedItem("etiqueta").toString();
							String telefono = nodoContacto.getTextContent();

							telefonos.put(tipoTelefono, telefono);
							break;

						case "Correu":
							String tipoCorreo = nodoContacto.getAttributes().getNamedItem("etiqueta").toString();
							String correo = nodoContacto.getTextContent();

							telefonos.put(tipoCorreo, correo);
							break;

						default:
							break;
						}
					}
				}

				ContactosList.add(new Contacto(id, nombre, apellidos, departamento, telefonos, correos));
			}
		}

		return ContactosList;
	}

	public static void writeResumTXT(List<Contacto> listContactos) throws IOException {
		final String SEPARACION = "---------------------------------------------";

		File directory = new File("agenda");
	    if (!directory.exists()){
	        directory.mkdir();
	    }
		
		File file = new File(AgendaMain.TXT_FILE);
		BufferedWriter bf = new BufferedWriter(new FileWriter(file));

		for (Contacto contacto : listContactos) {
			String strContacto = "";

			strContacto += "Contacte: " + contacto.getId() + "-" + contacto.getNombre() + " " + contacto.getApellidos()
					+ "(" + contacto.getDepartamento().getNombre() + "-" + contacto.getDepartamento().getAsignatura()
					+ ")\n";

			if (contacto.getTelefonos().size() != 0) {
				for (Entry<String, String> entry : contacto.getTelefonos().entrySet()) {
					strContacto += "Telèfons: \n";
					strContacto += "\t" + entry.getKey() + ":" + entry.getValue() + "\n";

				}
			}

			if (contacto.getCorreos().size() != 0) {
				for (Entry<String, String> entry : contacto.getCorreos().entrySet()) {
					strContacto += "Correus: \n";
					strContacto += "\t" + entry.getKey() + ":" + entry.getValue() + "\n";
				}
			}

			strContacto += SEPARACION + "\n";

			bf.write(strContacto);
		}
		
		bf.close();
	}

	public static void readResumTXT() throws IOException {
		File file = new File(AgendaMain.TXT_FILE);
		BufferedReader br = new BufferedReader(new FileReader(file));

		String linea = "";

		while ((linea = br.readLine()) != null) {
			System.out.println(linea);
		}

		br.close();
	}

	public static void insertContact(Document doc) {
		Scanner scanner = new Scanner(System.in);

		// Recogemos el nodo raíz para tratar el programa desde ahí:
		Node rootNode = doc.getFirstChild();

		// Datos del nuevo contacto que vamos a generar:
		String id = "12345678X";
		String nombre = "Marga";
		String apellidos = "Castañon";
		Departamento departamento = new Departamento("Informática", "Programacio");
		HashMap<String, String> telefonos = new HashMap<String, String>();
		// Creamos tantos números como queramos:
		telefonos.put("personal", "444444444");

		HashMap<String, String> correos = new HashMap<String, String>();
		// Creamos tantos correos como queramos:

		// Proceso de creación del nuevo nodo contacto:
		Element elementContacto = doc.createElement("Contacte");
//		contacto.setAttribute("data_publicacio", fechaPublicacion);

		// CREAMOS IDENTIFICACIÓN:
		Element elementIdentificacio = doc.createElement("Identificacio");
		elementIdentificacio.setAttribute("nif", id);

		Element elementNombrePersona = doc.createElement("Nom");
		elementNombrePersona.appendChild(doc.createTextNode(nombre));

		Element elementApellidoPersona = doc.createElement("Apellidos");
		elementApellidoPersona.appendChild(doc.createTextNode(apellidos));

		// AÑADIMOS EL NOMBRE Y APELLIDOS DE LA PERSONA A LA IDENTIFICACIÓN:
		elementIdentificacio.appendChild(elementNombrePersona);
		elementIdentificacio.appendChild(elementApellidoPersona);

		// AÑADIMOS LA IDENTIFICACIÓN AL CONTACTO:
		elementContacto.appendChild(elementIdentificacio);

		// CREAMOS DEPARTAMENTO:
		Element elementDepartamento = doc.createElement("Departament");

		Element nombreDepartamento = doc.createElement("Nom");
		nombreDepartamento.appendChild(doc.createTextNode(departamento.getNombre()));

		Element asignaturaDepartamento = doc.createElement("Assignatura");
		asignaturaDepartamento.appendChild(doc.createTextNode(departamento.getAsignatura()));

		// AÑADIMOS EL NOMBRE Y APELLIDOS DE LA PERSONA A LA IDENTIFICACIÓN:
		elementDepartamento.appendChild(nombreDepartamento);
		elementDepartamento.appendChild(asignaturaDepartamento);

		// AÑADIMOS EL DEPARTAMENTO AL CONTACTO:
		elementContacto.appendChild(elementDepartamento);

		// CREAMOS TELEFONO SI HAY::
		if (telefonos.size() != 0) {
			for (Entry<String, String> entry : telefonos.entrySet()) {
				// CREAMOS TELEFONO:
				Element elementTelefono = doc.createElement("Telefon");
				elementTelefono.setAttribute("etiqueta", entry.getKey());
				elementTelefono.appendChild(doc.createTextNode(entry.getValue()));

				// AÑADIMOS EL TELEFONO AL CONTACTO:
				elementContacto.appendChild(elementTelefono);
			}
		}

		// CREAMOS CORREOS SI HAY:
		if (correos.size() != 0) {
			for (Entry<String, String> entry : correos.entrySet()) {
				// CREAMOS CORREO:
				Element elementCorreo = doc.createElement("Correu");
				elementCorreo.setAttribute("etiqueta", entry.getKey());
				elementCorreo.appendChild(doc.createTextNode(entry.getValue()));

				// AÑADIMOS EL TELEFONO AL CONTACTO:
				elementContacto.appendChild(elementCorreo);
			}
		}
		
		rootNode.appendChild(elementContacto);
		
		// Probamos a crear el archivo resultante con el nuevo contacto:
		try {
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = transformerFactory.newTransformer();
			DOMSource source = new DOMSource(doc);

			StreamResult result = new StreamResult(new File(AgendaMain.FINAL_XML_FILE));
			transformer.transform(source, result);

			System.out.println("Contacto insertado con éxito!!");
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}

		scanner.close();
	}
}
