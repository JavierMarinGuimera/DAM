package manager;

import impls.AuthorsServiceImpl;
import impls.BooksServiceImpl;
import impls.LanguageServiceImpl;
import service.AuthorsService;
import service.BooksService;
import service.LanguageService;

public class DAOManager {
    public static final int JDBC = 1;
    public static final int HIBERNATE = 2;

    public static AuthorsService as;
    public static BooksService bs;
    public static LanguageService ls;

    public static AuthorsService getAuthorsService(int option) {
        if (as == null) {
            switch (option) {
                case JDBC:
                    // this can be created on the future.
                    break;

                case HIBERNATE:
                    as = new AuthorsServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return as;
    }

    public static BooksService getBooksService(int option) {
        if (bs == null) {
            switch (option) {
                case JDBC:
                    // this can be created on the future.
                    break;

                case HIBERNATE:
                    bs = new BooksServiceImpl();
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return bs;
    }

    public static LanguageService getLanguageService(int option) {
        if (ls == null) {
            switch (option) {
                case JDBC:
                    ls = new LanguageServiceImpl();
                    break;

                case HIBERNATE:
                    // this can be created on the future.
                    break;

                default:
                    System.out.println("Wrong option");
                    break;
            }
        }
        return ls;
    }
}
