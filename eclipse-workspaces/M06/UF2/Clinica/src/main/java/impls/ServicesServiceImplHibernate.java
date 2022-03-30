package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnectionHibernate;
import pojos.Serveis;
import services.ServicesService;

public class ServicesServiceImplHibernate implements ServicesService {

    @Override
    public List<Serveis> readAll() {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Serveis> criteria = builder.createQuery(Serveis.class);
            criteria.from(Serveis.class);
            List<Serveis> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean createOne(Serveis service) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(service.getCodi()) != null) {
                return false;
            }

            session.save(service);

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
    public Serveis readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Serveis.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateOne(Serveis service) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(service.getCodi()) == null) {
                return false;
            }

            session.update(service);

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
            Serveis service = null;
            if ((service = readOne(id)) == null) {
                return false;
            }

            session.delete(service);

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
