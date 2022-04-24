package models;

import java.util.List;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Company {

	private ObjectId id;
	
    @BsonProperty(value = "name")
    private String companyName;
    
    @BsonProperty(value = "category_code")
    private String categoryCode;
    
    @BsonProperty(value = "number_of_employees")
    private int numberEmployees;
    
    @BsonProperty(value = "founded_year")
    private int foundedYear;
    
    @BsonProperty(value = "description")
    private int description;
    
    @BsonProperty(value = "relationships")
    private List<Relationship> relationships;
    
    @BsonProperty(value = "total_money_raised")
    private double totalMoney;

	@BsonProperty(value = "countries")
    private List<String> countries;
	
	/**
	 * Main constructor
	 * 
	 * @param id
	 * @param companyName
	 * @param categoryCode
	 * @param numberEmployees
	 * @param foundedYear
	 * @param description
	 * @param relationships
	 * @param totalMoney
	 * @param countries
	 */
	public Company(ObjectId id, String companyName, String categoryCode, int numberEmployees, int foundedYear,
			int description, List<Relationship> relationships, double totalMoney, List<String> countries) {
		super();
		this.id = id;
		this.companyName = companyName;
		this.categoryCode = categoryCode;
		this.numberEmployees = numberEmployees;
		this.foundedYear = foundedYear;
		this.description = description;
		this.relationships = relationships;
		this.totalMoney = totalMoney;
		this.countries = countries;
	}

	/**
	 * Getters and setters.
	 */
	public ObjectId getId() {
		return id;
	}

	public void setId(ObjectId id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public int getNumberEmployees() {
		return numberEmployees;
	}

	public void setNumberEmployees(int numberEmployees) {
		this.numberEmployees = numberEmployees;
	}

	public int getFoundedYear() {
		return foundedYear;
	}

	public void setFoundedYear(int foundedYear) {
		this.foundedYear = foundedYear;
	}

	public int getDescription() {
		return description;
	}

	public void setDescription(int description) {
		this.description = description;
	}

	public List<Relationship> getRelationships() {
		return relationships;
	}

	public void setRelationships(List<Relationship> relationships) {
		this.relationships = relationships;
	}

	public double getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(double totalMoney) {
		this.totalMoney = totalMoney;
	}

	public List<String> getCountries() {
		return countries;
	}

	public void setCountries(List<String> countries) {
		this.countries = countries;
	}
}
