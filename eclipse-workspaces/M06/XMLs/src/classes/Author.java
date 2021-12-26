package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import globalThings.SAXManager;

public class Author {

	private String authorType;
	private String authorMembers;
	private String authorCountry;
	private String authorName;
	private Map<String, String> authorAlbums = new TreeMap<>();

	public Author() {
	}

	public Author(String authorType, String authorMembers, String authorCountry, String authorName,
			Map<String, String> authorAlbums) {
		this.authorType = authorType;
		this.authorMembers = authorMembers;
		this.authorCountry = authorCountry;
		this.authorName = authorName;
		this.authorAlbums = authorAlbums;
	}


	public String getAuthorType() {
		return authorType;
	}

	public void setAuthorType(String authorType) {
		this.authorType = authorType;
	}

	public String getAuthorMembers() {
		return authorMembers;
	}

	public void setAuthorMembers(String authorMembers) {
		this.authorMembers = authorMembers;
	}

	public String getAuthorCountry() {
		return authorCountry;
	}

	public void setAuthorCountry(String authorCountry) {
		this.authorCountry = authorCountry;
	}

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}

	public Map<String, String> getAuthorAlbums() {
		return authorAlbums;
	}

	public void setAuthorAlbums(String key, String val) {
		this.authorAlbums.put(key, val);
	}

	@Override
	public String toString() {
		
		try {
			File file = new File(SAXManager.OUTPUT_FILE);
			BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
			
			String str = "Autor: " + authorName + " (" + authorCountry + ") - "
					+ (authorType.equals("Grup") ? "Grup (" + authorMembers + " components)" : "Solista \n");
			
			for (Map.Entry<String, String> entry : authorAlbums.entrySet())
				str += entry.getKey() + ": " + entry.getValue() + "\n";
			
			bf.write(str + "\n");
			
			bf.close();
			
			return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
