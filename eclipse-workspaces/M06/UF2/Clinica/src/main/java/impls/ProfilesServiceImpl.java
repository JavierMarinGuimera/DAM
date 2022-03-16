package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import pojos.Perfils;
import services.ProfilesService;

public class ProfilesServiceImpl implements ProfilesService {

    @Override
    public List<Perfils> readAll() {
        Session session = DBConnection.getSessionFactory().openSession();

        try {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Perfils> criteria = builder.createQuery(Perfils.class);
            criteria.from(Perfils.class);
            List<Perfils> data = session.createQuery(criteria).getResultList();
            return data;
        } catch (Exception e) {
            System.out.println("No se han podido obtener los usuarios.");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean createOne(Perfils perfil) {
        Session session = DBConnection.getSessionFactory().openSession();
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
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean updateOne(int id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deleteOne(int id) {
        // TODO Auto-generated method stub
        return false;
    }

}
