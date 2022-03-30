package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.annotations.Check;
import org.hibernate.query.Query;

import connection.DBConnectionHibernate;
import pojos.Assistencies;
import services.AttendanceService;

public class AttendanceServiceImplHibernate implements AttendanceService {

    @Override
    public List<Assistencies> readAll() {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Assistencies> criteria = builder.createQuery(Assistencies.class);
            criteria.from(Assistencies.class);
            List<Assistencies> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean createOne(Assistencies attendance) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(attendance.getCodiAssistencia()) != null) {
                return false;
            }

            session.save(attendance);

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
    public Assistencies readOne(int id) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
            return session.get(Assistencies.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean updateOne(Assistencies attendance) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (readOne(attendance.getCodiAssistencia()) == null) {
                return false;
            }

            session.update(attendance);

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
            Assistencies attendance = null;
            if ((attendance = readOne(id)) == null) {
                return false;
            }

            session.delete(attendance);

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
    public List<Assistencies> readAllById(String userId) {
        Session session = DBConnectionHibernate.getSessionFactory().openSession();

        try {
        } catch (Exception e) {
            List<Assistencies> data = session.createQuery("FROM Assistencies a WHERE a.assistent=" + userId)
                    .list();
            System.out.println("Hola" + data.size());
            return data;
        } finally {
            session.close();
        }
        return null;
    }

}
