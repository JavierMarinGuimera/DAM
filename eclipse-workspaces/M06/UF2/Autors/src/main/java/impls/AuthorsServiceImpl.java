package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnectionHibernate;
import pojos.Autors;
import pojos.Llibres;
import service.AuthorsService;

public class AuthorsServiceImpl implements AuthorsService {

    @Override
    public List<Autors> readAll() {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Autors> criteria = builder.createQuery(Autors.class);
            criteria.from(Autors.class);
            List<Autors> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public Autors readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Autors.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean createOne(Autors author) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(author.getCodiAutor()) != null) {
                return false;
            }

            if (author.getLlibreses() == null) {
                session.save(author);
            } else {
                Autors authorPlantilla = new Autors(author.getCodiAutor(), author.getNom());
                session.save(authorPlantilla);
                
                for (Llibres book : author.getLlibreses()) {
                    session.save(book);
                }
            }

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el usuario.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }
}
