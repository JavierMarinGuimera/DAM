package main;

import java.util.ArrayList;
import java.util.List;

import managers.DAOManager;
import models.Company;
import models.Person;
import models.Relationship;
import services.CompaniesMongoService;

public class App {

	private static CompaniesMongoService cms;

	public static void main(String[] args) {
		showWelcomeMessage();
		testCompaniesDAO();
	}

	private static void showWelcomeMessage() {
		System.out.println("___DAO test with MongoDB. Welcome!____");
		System.err.println("___ATTENTION! NOTHING IS ASKED TO THE USER ON THIS DAO TEST!___ \n");
	}

	private static void testCompaniesDAO() {
		cms = DAOManager.getCompaniesService(DAOManager.MONGODB);

		// Create one.
		Person person = new Person("Prueba", "Prueba", "Prueba");
		Relationship relationship = new Relationship(true, "Prueba", person);
		List<Relationship> relationshipList = new ArrayList<>() {
			{
				add(relationship);
			}
		};
		
		Company company = new Company("Prueba", "Prueba", 0, 0, "Prueba", relationshipList, "Prueba", new ArrayList<>() {{
			add("Prueba");
		}});
		
		boolean created = cms.createOne(company);
		System.out.println("The company " + (created ? "was" : "wasn't") + " created!\n");
		
		// Read one.
		System.out.println("Read one operation (reading Prueba)");
		System.out.println(cms.readOne("Prueba") + "\n");

		// Update one.
		boolean updated = cms.updateOne("Prueba");
		System.out.println("The company " + (updated ? "was" : "wasn't") + " updated!\n");
		
		// Delete one.
		boolean deleted = cms.deleteOne("Prueba");
		System.out.println("The company " + (deleted ? "was" : "wasn't") + " deleted!\n");
	}
}
