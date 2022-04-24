package models;

import org.bson.codecs.pojo.annotations.BsonProperty;

public class Person {
	@BsonProperty(value = "first_name")
	private String firstName;
	
	@BsonProperty(value = "last_name")
	private String lastName;
	
	@BsonProperty(value = "permalink")
	private String permalink;
}
