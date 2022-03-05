package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import pojos.Empleats;
import services.EmpleadosService;

public class EmpeladosServiceImplHibernate implements EmpleadosService {

    @Override
    public List<Empleats> getAll() {
        Session session = DBConnection.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Empleats> criteria = builder.createQuery(Empleats.class);
            criteria.from(Empleats.class);
            List<Empleats> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            System.out.println("No se han podido obtener los empleados.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Empleats selectOne(int empNo) {
        Session session = DBConnection.getSessionFactory().openSession();

        try {
            return session.get(Empleats.class, empNo);
        } catch (Exception e) {
            System.out.println("No se han podido obtener el empleado.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean createOne(Empleats empleado) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (selectOne(empleado.getEmpNo()) != null) {
                return false;
            }

            session.save(empleado);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el empleado.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean updateOne(Empleats empleado) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (selectOne(empleado.getEmpNo()) == null) {
                return false;
            }

            session.update(empleado);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el empleado.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteOne(Empleats empleado) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (selectOne(empleado.getEmpNo()) == null) {
                return false;
            }

            session.delete(empleado);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el empleado.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

}
