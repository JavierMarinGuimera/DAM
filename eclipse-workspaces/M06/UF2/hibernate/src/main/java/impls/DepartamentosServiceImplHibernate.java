package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import pojos.Departaments;
import services.DepartamentosService;

public class DepartamentosServiceImplHibernate implements DepartamentosService {

    @Override
    public List<Departaments> getAll() {
        Session session = DBConnection.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Departaments> criteria = builder.createQuery(Departaments.class);
            criteria.from(Departaments.class);
            List<Departaments> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            System.out.println("No se han podido obtener los departamentos.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Departaments selectOne(int deptNo) {
        Session session = DBConnection.getSessionFactory().openSession();

        try {
            return session.get(Departaments.class, (byte) deptNo);
        } catch (Exception e) {
            System.out.println("No se han podido obtener el departamento.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean createOne(Departaments departamento) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            if (selectOne(departamento.getDeptNo()) != null) {
                return false;
            }

            session.save(departamento);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el departamento.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean updateOne(int deptNo) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Departaments departamento = selectOne(deptNo);

            if (departamento == null) {
                return false;
            }

            departamento.setDeptCiutat("Pruebas");

            session.update(departamento);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el departamento.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public Boolean deleteOne(int deptNo) {
        Session session = DBConnection.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        try {
            Departaments departamento = selectOne(deptNo);

            if (departamento == null) {
                return false;
            }

            session.delete(departamento);

            session.getTransaction().commit();

            return true;
        } catch (Exception e) {
            System.out.println("No se han podido crear el departamento.");
            tx.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }
}
