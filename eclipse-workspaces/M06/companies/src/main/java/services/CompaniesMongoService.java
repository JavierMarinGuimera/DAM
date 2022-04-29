package services;

import models.Company;

public interface CompaniesMongoService {
	public boolean createOne(Company company);
	
	public Company readOne(String name);
	
	public boolean updateOne(String name);
	
	public boolean deleteOne(String name);
}
