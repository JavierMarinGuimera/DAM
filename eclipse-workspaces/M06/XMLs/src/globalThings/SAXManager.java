package globalThings;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import classes.Album;
import classes.Author;
import exercises.DiscografiaMain;

public class SAXManager extends DefaultHandler {
	public static final String OUTPUT_TXT_FILE = "files/DiscografiaResum.txt";

	public enum variables {
		TRASH, AUTHOR, NAME, ALBUM
	};

	private variables currentElement = variables.TRASH;
	private List<Author> authors = new ArrayList<>();
	private int currentAuthor = 0;
	private String albumDate;
	private String albumName;

	@Override
	public void startDocument() throws SAXException {
		System.out.println("Comenzamos a leer el fichero: \n");
		new File(OUTPUT_TXT_FILE).delete();
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals("Autor")) {
			currentElement = variables.AUTHOR;
			
			authors.add(new Author());
			authors.get(currentAuthor).setAuthorType(attributes.getValue("tipus"));
			authors.get(currentAuthor).setAuthorMembers(attributes.getValue("num_components"));
		} else if (qName.equals("Nom")) {
			currentElement = variables.NAME;
			authors.get(currentAuthor).setAuthorCountry(attributes.getValue("pais"));
		} else if (qName.equals("Album")) {
			currentElement = variables.ALBUM;
			albumDate = attributes.getValue("data_publicacio");
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals("Autor")) {
			System.out.println(authors.get(currentAuthor));
			System.out.println(DiscografiaMain.SEPARATION + "\n");
			currentAuthor++;
		}
		
		if (qName.equals("Album")) {
			authors.get(currentAuthor).addAuthorAlbum(new Album(albumDate, albumName));
		}

		currentElement = variables.TRASH;
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		if (currentElement == variables.NAME) {
			authors.get(currentAuthor).setAuthorName(new String(ch, start, length));
		}
		
		if (currentElement == variables.ALBUM) {
			albumName = new String(ch, start, length);
		}
	}

	@Override
	public void endDocument() throws SAXException {
		System.out.println("¡Fichero leído al completo!");
	}
	
	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
}
