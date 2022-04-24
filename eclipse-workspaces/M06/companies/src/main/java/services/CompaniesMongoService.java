package services;

import models.Company;

public interface CompaniesMongoService {
	public boolean createOne(Company company);
	
	public Company readOne(int id);
	
	public boolean updateOne(Company company);
	
	public boolean deleteOne(int id);
}
