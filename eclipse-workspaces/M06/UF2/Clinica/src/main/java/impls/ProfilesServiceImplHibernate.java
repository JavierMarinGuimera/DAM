package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnectionHibernate;
import pojos.Perfils;
import services.ProfilesService;

public class ProfilesServiceImplHibernate implements ProfilesService {

    @Override
    public List<Perfils> readAll() {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Perfils> criteria = builder.createQuery(Perfils.class);
            criteria.from(Perfils.class);
            List<Perfils> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean createOne(Perfils perfil) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(perfil.getCodi()) != null) {
                return false;
            }

            session.save(perfil);

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
    public Perfils readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Perfils.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateOne(Perfils perfil) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(perfil.getCodi()) == null) {
                return false;
            }

            session.update(perfil);

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
            Perfils perfil = null;
            if ((perfil = readOne(id)) == null) {
                return false;
            }

            session.delete(perfil);

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
