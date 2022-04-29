package models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Relationship {
	
	@BsonProperty(value = "is_past")
    private boolean isPast;

	@BsonProperty(value = "title")
	private String title;
	
	@BsonProperty(value = "person")
	private Person person;
	
	public Relationship() {
		
	}
	
	/**
	 * Main constructor.
	 * @param isPast
	 * @param title
	 * @param person
	 */
	public Relationship(boolean isPast, String title, Person person) {
		super();
		this.isPast = isPast;
		this.title = title;
		this.person = person;
	}
	
	/**
	 * Getters and setters.
	 */

	public boolean isPast() {
		return isPast;
	}

	public void setPast(boolean isPast) {
		this.isPast = isPast;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	@Override
	public String toString() {
		return "Relationship [isPast=" + isPast + ", title=" + title + ", person=" + person + "]";
	}
}
