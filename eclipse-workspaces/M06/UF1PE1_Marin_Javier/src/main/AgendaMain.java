package main;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import classes.Contacto;
import globalThings.XMLDOMManager;

public class AgendaMain {
	public static final String MAIN_FILE = "files/Agenda2XML.xml";
	public static final String TXT_FILE = "agenda/ResumContactes.txt";
	public static final String FINAL_XML_FILE = "agenda/AgendaXMLModificat.xml";

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		// Nos creamos la factoria para recoger el archivo xml y parsearlo.
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(true);

		DocumentBuilder builder = factory.newDocumentBuilder();

		File contactos = new File(MAIN_FILE);

		Document doc = builder.parse(contactos);

		// Nodo root para hacer todo desde la raíz.
		Node rootNode = doc.getFirstChild();

		// Recoger datos del fichero y recoger los contactos.
		List<Contacto> listContactos = XMLDOMManager.constructContactosList(rootNode);

		// Escribir fichero.txt resumen con los datos recogidos. 
		XMLDOMManager.writeResumTXT(listContactos);
		
		// Leer fichero resumen y mostrar contenido por pantalla.
		XMLDOMManager.readResumTXT();
		
		// Añadir un nodo contacto nuevo y guardar en un nuevo archivo.xml.
		XMLDOMManager.insertContact(doc);
	}

}
