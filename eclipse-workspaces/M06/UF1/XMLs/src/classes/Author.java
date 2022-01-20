package classes;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import globalThings.SAXManager;

public class Author {

	private String authorType;
	private String authorMembers;
	private String authorCountry;
	private String authorName;
	private List<Contacto> authorAlbums = new ArrayList<>();

	public Author() {
	}

	public Author(String authorType, String authorMembers, String authorCountry, String authorName,
			List<Contacto> authorAlbums) {
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

	public List<Contacto> getAuthorAlbums() {
		return authorAlbums;
	}
	
	public void setAuthorAlbums(List<Contacto> albums) {
		this.authorAlbums = albums;
	}

	public void addAuthorAlbum(Contacto album) {
		this.authorAlbums.add(album);
	}

	@Override
	public String toString() {
		
		try {
			File file = new File(SAXManager.OUTPUT_TXT_FILE);
			BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
			
			String str = "Autor: " + authorName + " (" + authorCountry + ") - "
					+ (authorType.equals("Grup") ? "Grup (" + authorMembers + " components) \n" : "Solista \n");
			
			for (Contacto album : authorAlbums) {
				str += album.getDate() + ": " + album.getName() + "\n";
			}
			
			bf.write(str);
			
			bf.close();
			
			return str;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
