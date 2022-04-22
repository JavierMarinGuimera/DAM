package mongodbexample;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

public class HelloMongoDB {
	public static void main(String[] args) {
		/**
		 * Create sense paràmetres connecta a localhost. Si volem connectar a un altre
		 * se li ha de passar per paràmetres el string de connexió.:
		 * MongoClients.create(
		 * "mongodb+srv://m001-student:m001-student@marianao.zmw5t.mongodb.net/?retryWrites=true&w=majority")
		 */
		try (MongoClient mongoClient = MongoClients.create()) {

			List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
			databases.forEach(db -> System.out.println(db.toJson()));
		}
	}
}