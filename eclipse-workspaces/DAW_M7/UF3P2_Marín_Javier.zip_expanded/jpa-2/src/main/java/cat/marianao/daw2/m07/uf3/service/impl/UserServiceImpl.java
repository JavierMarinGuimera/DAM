package cat.marianao.daw2.m07.uf3.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.service.UserService;

@Stateless
@TransactionManagement(TransactionManagementType.BEAN)
public class UserServiceImpl implements UserService {
	@PersistenceContext(unitName = "UserPersistenceUnit")
	private EntityManager entityManager;

	@Resource
	private EJBContext context;

	@Override
	public void create(User user) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			entityManager.persist(user);
			utx.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			utx.rollback();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void edit(User user) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			entityManager.merge(user);
			utx.commit();
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@Override
	public void remove(User user) {
		UserTransaction utx = context.getUserTransaction();
		try {
			utx.begin();
			entityManager.remove(user);
			utx.commit();
		} catch (Exception e) {
			try {
				utx.rollback();
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		return entityManager.createQuery("select * from users").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findActiveUsers() throws SQLException {
		System.out.println("entra");
		return entityManager.createQuery("select * from users " + "where active = 1")
				.getResultList();
	}

	@Override
	public User findUserByUsername(String username) {
		return (User) entityManager.createQuery("select object(o) from User o " + "where o.username = ?")
				.setParameter(1, username).getSingleResult();
	}

	@Override
	public User findUserByEmail(String mail) {
		return (User) entityManager.createQuery("select object(o) from User o " + "where o.email = ?")
				.setParameter(1, mail).getSingleResult();
	}

	@Override
	public User createUser(User user) {
		entityManager.createNativeQuery("INSERT INTO users (username, name, email, password) VALUES (?, ?, ? ,?)")
				.setParameter(1, user.getUsername()).setParameter(2, user.getName()).setParameter(3, user.getEmail())
				.setParameter(4, user.getPassword()).executeUpdate();
		return user;
	}

	@Override
	public User setUserInactive(User user) {
		entityManager.createQuery("UPDATE `users` SET `active`= false WHERE name = ?").setParameter(1, user.getName());
		return user;
	}
}
