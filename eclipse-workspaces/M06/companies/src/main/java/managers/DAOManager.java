package managers;

import impls.CompaniesMongoServiceImpl;
import services.CompaniesMongoService;

public class DAOManager {
	
	public static final int MONGODB = 0;
	public static final int JDBC = 1;
	public static final int HIBERNATE = 2;

	private static CompaniesMongoService cs;

	private DAOManager() {
	}

	public static CompaniesMongoService getCompaniesService(int dbType) {
		switch (dbType) {
		case MONGODB:
			cs = new CompaniesMongoServiceImpl();
			break;
			
		case JDBC:
			
			break;
			
		case HIBERNATE:
			
			break;

		default:
			System.err.println("Wrong option for the creation!");
			break;
		}

		return cs;
	}
}
