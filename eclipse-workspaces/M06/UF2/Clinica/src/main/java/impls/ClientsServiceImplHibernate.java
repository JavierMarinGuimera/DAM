package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnectionHibernate;
import pojos.Clients;
import services.ClientsService;

public class ClientsServiceImplHibernate implements ClientsService {

    @Override
    public List<Clients> readAll() {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Clients> criteria = builder.createQuery(Clients.class);
            criteria.from(Clients.class);
            List<Clients> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean createOne(Clients client) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(client.getIdClient()) != null) {
                return false;
            }

            session.save(client);

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

    @Override
    public Clients readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Clients.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateOne(Clients client) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(client.getIdClient()) == null) {
                return false;
            }

            session.update(client);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public boolean deleteOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Clients client = null;
            if ((client = readOne(id)) == null) {
                return false;
            }

            session.delete(client);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return false;
    }

}
