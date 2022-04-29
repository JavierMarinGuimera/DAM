package managers;

import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import models.Company;

public class ConnectionManager {
	public static final String URL = "mongodb://localhost";
	public static final String CURRENT_DB = "companies";
	public static final String COMPANIES_COLLECTION = "companies";

	private static MongoClientSettings mongoClientSettings;
	private static MongoClient mongoClient;
	private static MongoCollection<Company> companiesCollection;

	private ConnectionManager() {
	}

	static {
		ConnectionString connectionString = new ConnectionString(URL);
		CodecRegistry pojoCodecRegistry = CodecRegistries
				.fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
				pojoCodecRegistry);

		mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.codecRegistry(codecRegistry).build();
	}

	public static MongoDatabase getConnection() {
		if (mongoClient == null) {
			// MongoClients.create() vacio para que conecte con localhost.
			mongoClient = MongoClients.create(mongoClientSettings);
		}

		return mongoClient.getDatabase(CURRENT_DB);
	}

	public static MongoCollection<Company> getCompaniesCollection() {
		if (companiesCollection == null) {
			companiesCollection = ConnectionManager.getConnection().getCollection(ConnectionManager.COMPANIES_COLLECTION, Company.class);			
		}
		
		return companiesCollection;
	}
}
