package impls;

import org.bson.Document;

import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;

import managers.ConnectionManager;
import models.Company;
import services.CompaniesMongoService;

public class CompaniesMongoServiceImpl implements CompaniesMongoService {

	@Override
	public boolean createOne(Company company) {
		if (readOne(company.getCompanyName()) != null) {
			return false;
		}
		
		ConnectionManager.getCompaniesCollection().insertOne(company);
		return true;
	}

	@Override
	public Company readOne(String name) {
		return ConnectionManager.getCompaniesCollection().find(Filters.eq("name", name)).first();
	}

	@Override
	public boolean updateOne(String name) {
		Company company = readOne(name);
		
		if (company == null) {
			return false;
		}
		
		String updatedName = "Prueba";
		
		System.out.println("Modifying company name.");
		company.setCompanyName(updatedName);
		
		boolean updated = ConnectionManager.getCompaniesCollection().findOneAndReplace(new Document("name", name), company,new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER)).getCompanyName().equals(updatedName);
		return updated;
	}

	@Override
	public boolean deleteOne(String name) {
		return ConnectionManager.getCompaniesCollection().deleteOne(new Document("name", name)).getDeletedCount() > 0;
	}

}
