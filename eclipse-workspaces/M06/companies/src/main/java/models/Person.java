package models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Person {

	@BsonProperty(value = "first_name")
	private String firstName;
	
	@BsonProperty(value = "last_name")
	private String lastName;
	
	@BsonProperty(value = "permalink")
	private String permalink;
	
	public Person() {
		
	}

	public Person(String firstName, String lastName, String permalink) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.permalink = permalink;
	}


	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPermalink() {
		return permalink;
	}

	public void setPermalink(String permalink) {
		this.permalink = permalink;
	}
	
	@Override
	public String toString() {
		return "Person [firstName=" + firstName + ", lastName=" + lastName + ", permalink=" + permalink + "]";
	}
}
