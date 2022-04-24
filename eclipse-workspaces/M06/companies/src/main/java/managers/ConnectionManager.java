package managers;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class ConnectionManager {
	public static final String CURRENT_DB = "companies";
	public static final String CURRENT_COLLECTION = "companies";
	
	private static MongoClient mongoClient;
	

	private ConnectionManager() {}

	public static MongoClient getConnection() {
		if (mongoClient == null) {
			mongoClient = MongoClients.create();
		}
		
		// TODO - Devolver conexión directa a la base de datos.
		return mongoClient;
	}
}
