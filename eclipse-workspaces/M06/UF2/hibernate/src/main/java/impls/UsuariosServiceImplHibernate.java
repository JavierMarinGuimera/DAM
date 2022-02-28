package impls;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import pojos.Usuaris;

import org.hibernate.Session;
import org.hibernate.Transaction;

import connection.DBConnection;
import services.UsuariosService;

public class UsuariosServiceImplHibernate implements UsuariosService {

	@Override
	public List<Usuaris> getAll() {
		Session session = DBConnection.getSessionFactory().openSession();

		try {
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Usuaris> criteria = builder.createQuery(Usuaris.class);
			criteria.from(Usuaris.class);
			List<Usuaris> data = session.createQuery(criteria).getResultList();
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
	public Usuaris selectOne(int userId) {
		Session session = DBConnection.getSessionFactory().openSession();

		try {
			return session.get(Usuaris.class, userId);
		} catch (Exception e) {
			System.out.println("No se han podido obtener el usuario.");
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Boolean createOne(Usuaris user) {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			if (selectOne(user.getUserid()) != null) {
				return false;
			}

			session.save(user);

			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			System.out.println("No se han podido crear el usuario.");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Boolean updateOne(Usuaris user) {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			if (selectOne(user.getUserid()) == null) {
				return false;
			}

			session.update(user);

			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			System.out.println("No se han podido crear el usuario.");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public Boolean deleteOne(Usuaris user) {
		Session session = DBConnection.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();

		try {
			if (selectOne(user.getUserid()) == null) {
				return false;
			}

			session.delete(user);

			session.getTransaction().commit();

			return true;
		} catch (Exception e) {
			System.out.println("No se han podido crear el usuario.");
			tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
