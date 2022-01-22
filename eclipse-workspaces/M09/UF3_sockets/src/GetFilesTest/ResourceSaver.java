package GetFilesTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

public class ResourceSaver {
	private static final String SOURCE_DIRECTORY = "directories/";

	private Map<String, String> resourcesTypes;
	private List<String> resources;

	public ResourceSaver() {
		super();
		resourcesTypes = new HashMap<String, String>();
		resources = new ArrayList<String>();
	}

	public Map<String, String> getResourcesTypes() {
		return resourcesTypes;
	}

	public void setResourcesTypes(Map<String, String> resourcesTypes) {
		this.resourcesTypes = resourcesTypes;
	}

	public List<String> getResources() {
		return resources;
	}

	public void setResources(List<String> resources) {
		this.resources = resources;
	}

	public void addResourceType(String fileType, String folder) {
		if (!resourcesTypes.containsKey(fileType)) {
			resourcesTypes.put(fileType, folder);
		} else {
			System.out.println("El recurso ya está en la lista.");
		}
	}

	public void createFolderTree() {
		if (!new File(SOURCE_DIRECTORY).exists()) {
			new File(SOURCE_DIRECTORY).mkdir();
			System.out.println("Carpeta 'directories' no existía y se ha creado!");
		}

		for (Entry<String, String> entry : resourcesTypes.entrySet()) {
			new File(SOURCE_DIRECTORY + entry.getValue()).mkdir();
			System.out.println("Created " + entry.getValue() + " folder!");
		}
	}

	public void saveResource(String resource) throws IOException {
		String fileName = new File(resource).getName();
		String endFolder = "";
		System.out.println("Se va a generar el archivo: " + fileName);

		// Crear URL.
		URL url = new URL(resource);
		URLConnection con = url.openConnection();

		// Dónde hay que descargárselo?
		for (Entry<String, String> entry : resourcesTypes.entrySet()) {
			if (isXFormat(entry.getKey(), url, con)) {
				endFolder = entry.getValue();
				break;
			}
		}

		File outputFile = new File(SOURCE_DIRECTORY + endFolder + "/" + fileName);

		System.out.println("El archivo se va a guardar en la carpeta: directories/" + endFolder);

		// Coger stream.
		InputStream commingFile = url.openStream();
		InputStreamReader isr = new InputStreamReader(commingFile);
		FileWriter fw = new FileWriter(outputFile);

		// Leer y escribir en fichero.
		char[] cbuf = new char[512];
		int readedChars;
		while ((readedChars = isr.read(cbuf)) != -1) {
			fw.write(cbuf, 0, readedChars);
		}

		fw.close();

		System.out.println(con.getContentLength());
		System.out.println(outputFile.length());
		System.out.println("Fichero descargado con éxito!\n");
	}

	boolean isXFormat(String pattern, URL url, URLConnection con) {
		String headerType = con.getContentType();
		String guessType = URLConnection.guessContentTypeFromName(url.getFile());

		return (Pattern.matches(pattern, headerType) || (Pattern.matches(pattern, guessType)));
	}

}
