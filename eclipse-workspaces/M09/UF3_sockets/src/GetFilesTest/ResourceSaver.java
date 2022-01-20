package GetFilesTest;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ResourceSaver {

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
		for (Entry<String, String> entry : resourcesTypes.entrySet()) {
			new File(entry.getValue()).mkdir();
			System.out.println("Created " + entry.getValue() + " folder!");
		}
	}

	public void saveResource(String resource) throws IOException {
		URL url = new URL(resource);
		
//		if(!isText(url)){
//			 return;
//			}

		URLConnection con = url.openConnection();
		String headerType = con.getContentType();
		String guessType = URLConnection.guessContentTypeFromName(url.getFile());
		
		System.out.println(headerType);
		System.out.println(guessType);
		
		String type = "";
		for (Entry<String, String> entry : resourcesTypes.entrySet()) {
			if (headerType.endsWith(entry.getKey())  || guessType.endsWith(entry.getKey())) {
				type = entry.getValue();
				break;
			}
		}
		
		if (!type.equals("")) {
			if (new File(type).exists()) {				
				
			}
		}
	}

	boolean isXFormat(URL url) {
		boolean ret = false;
		try {
			URLConnection con = url.openConnection();
			String headerType = con.getContentType();
			String guessType = URLConnection.guessContentTypeFromName(url.getFile());

			System.out.println(headerType);
			System.out.println(guessType);
//			
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return ret;
	}

}
