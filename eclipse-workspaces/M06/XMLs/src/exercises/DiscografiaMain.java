package exercises;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

import globalThings.SAXManager;

public class DiscografiaMain {
	private static final String FILE = "files/DiscografiaXML.xml";
	

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		
		SAXParser SAXParser = factory.newSAXParser();
		
		SAXParser.parse(FILE, new SAXManager());
	}
}
