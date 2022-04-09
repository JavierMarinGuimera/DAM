package impls;

import org.hibernate.Session;

import connection.DBConnectionHibernate;
import pojos.Llibres;
import service.BooksService;

public class BooksServiceImpl implements BooksService {

    @Override
    public Llibres readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Llibres.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
