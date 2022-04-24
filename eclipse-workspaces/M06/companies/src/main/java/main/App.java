package main;

import managers.DAOManager;
import services.CompaniesMongoService;

public class App {
	
	private static CompaniesMongoService cms;

	public static void main(String[] args) {

		cms = DAOManager.getCompaniesService(DAOManager.MONGODB);
		
//		try (MongoClient mongoClient = ConnectionManager.getConnection()) {
//
//			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
//			databases.forEach(db -> System.out.println(db.toJson()));
//		}
	}

}
