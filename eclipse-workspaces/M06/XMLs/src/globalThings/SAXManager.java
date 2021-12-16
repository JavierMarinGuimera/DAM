package globalThings;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class SAXManager extends DefaultHandler{
	private static final String BOOK_SEPARATION = "--------------------------";
	
	private int currentElement = 0;
	private String currentLine = "";
	
	@Override
	public void startDocument() throws SAXException {
		System.out.println("El resultado es: \n");
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		System.out.println("Element: " + qName);
		
		if (qName.equals("Libro")) {
			currentElement = 1;
		} else if (qName.equals("Titulo")) {
			currentElement = 2;
		} else if (qName.equals("Autor")) {
			currentElement = 3;
		}
	}
	
	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("Libro")) {
			System.out.println(currentLine + "\n" + BOOK_SEPARATION);
			currentLine = "";
		}
		
		currentElement = 0;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentElement == 2 || currentElement == 3) {
			String text = ch.toString().trim();
			currentLine += "\n- El " + (currentElement == 2 ? "título" : "autor") + " es: " + text; 
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("Ya no hay más registros.");
	}
}
