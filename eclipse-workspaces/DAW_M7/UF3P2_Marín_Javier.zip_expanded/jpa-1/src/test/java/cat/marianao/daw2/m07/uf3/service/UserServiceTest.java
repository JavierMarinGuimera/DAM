package cat.marianao.daw2.m07.uf3.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.service.impl.UserServiceImpl;

public class UserServiceTest {
	private EntityManager entityManager;
	private EntityTransaction entityTransaction;
	private UserService userService;

	@Before
	public void setUp() {
		entityManager = Persistence.createEntityManagerFactory("InMemoryH2PersistenceUnit").createEntityManager();
		userService = new UserServiceImpl(entityManager);
		entityTransaction = entityManager.getTransaction();
	}

	@After
	public void cleanUp() {
		entityManager.close();
	}

	@Test
	public void findUserByUsername() {
		String username = "jdoe";
		User user0 = new User();
		user0.setActive(true);
		user0.setCreatedOn(new Timestamp(new Date().getTime()));
		user0.setEmail("test@test.com");
		user0.setName("Jane");
		user0.setPassword("password");
		user0.setRank(100);
		user0.setUsername(username);
		User user1 = new User();
		user1.setActive(true);
		user1.setCreatedOn(new Timestamp(new Date().getTime()));
		user1.setEmail("test1@test.com");
		user1.setName("Joe");
		user1.setPassword("password");
		user1.setRank(100);
		user1.setUsername("joeTest");

		entityTransaction.begin();
		userService.create(user0);
		userService.create(user1);
		entityTransaction.commit();

		User userFromDB = userService.findUserByUsername(username);
		Assert.assertNotNull(userFromDB);
		Assert.assertEquals("jdoe", userFromDB.getUsername());
		Assert.assertEquals("test@test.com", userFromDB.getEmail());
		Assert.assertNotNull(userFromDB.getUserId());
	}

	@Test
	public void editUser() {
		// Creación usuario prueba.
		User user2 = new User();
		user2.setActive(true);
		user2.setCreatedOn(new Timestamp(new Date().getTime()));
		user2.setEmail("test2@test.com");
		user2.setName("Joe");
		user2.setPassword("password");
		user2.setRank(100);
		user2.setUsername("editado");

		// Comprobar situación inicial.

		Assert.assertEquals(user2.getName(), "Joe");
		Assert.assertEquals(user2.getEmail(), "test2@test.com");
		Assert.assertEquals(user2.getPassword(), "password");

		// Comprobado que todo está correcto, creamos el usuario.
		entityTransaction.begin();
		userService.create(user2);
		entityTransaction.commit();

		// Recogemos el usuario de la base de datos y le modificamos algún dato.
		User userFromDB = userService.findUserByUsername("editado");

		Assert.assertNotNull(userFromDB);
		Assert.assertEquals(userFromDB.getName(), "Joe");
		Assert.assertEquals(userFromDB.getEmail(), "test2@test.com");
		Assert.assertEquals(userFromDB.getPassword(), "password");

		userFromDB.setEmail("modified@test.com");

		// Ahora editamos el usuario que acabamos de crear y nos devolverá el usuario ya
		// modificado de la base de datos.
		User modifiedUserFromDB = userService.edit(userFromDB);

		Assert.assertNotNull(modifiedUserFromDB);
		Assert.assertEquals(modifiedUserFromDB.getName(), "Joe");
		Assert.assertEquals(modifiedUserFromDB.getEmail(), "modified@test.com");
		Assert.assertEquals(modifiedUserFromDB.getPassword(), "password");

		entityTransaction.begin();
		userService.remove(modifiedUserFromDB);
		entityTransaction.commit();
	}

	@Test(expected = NoResultException.class)
	public void removeUser() {
		// Creación usuario prueba.
		User user3 = new User();
		user3.setActive(true);
		user3.setCreatedOn(new Timestamp(new Date().getTime()));
		user3.setEmail("test3@test.com");
		user3.setName("Joe");
		user3.setPassword("password");
		user3.setRank(100);
		user3.setUsername("pruebaEliminador");

		// Comprobado que todo está correcto, creamos el usuario.
		entityTransaction.begin();
		userService.create(user3);
		entityTransaction.commit();

		// Recogemos el usuario de la base de datos y lo eliminaremos de la base de
		// datos.
		User userFromDB = userService.findUserByUsername("pruebaEliminador");

		Assert.assertNotNull(userFromDB);
		Assert.assertEquals(userFromDB.getName(), "Joe");
		Assert.assertEquals(userFromDB.getUsername(), "pruebaEliminador");
		Assert.assertEquals(userFromDB.getEmail(), "test3@test.com");
		Assert.assertEquals(userFromDB.getPassword(), "password");

		entityTransaction.begin();
		userService.remove(userFromDB);
		entityTransaction.commit();

		// Ahora probamos a coger de la base de datos el "user3" y tendriamos que
		// recoger un nulo.
		userService.findUserByUsername("pruebaEliminador");
	}

	@Test
	public void findActiveUsers() throws SQLException {
		// Recoger los usuarios activos de la base de datos que deben ser 2.
		Assert.assertEquals(userService.findActiveUsers().size(), 2);
	}

	@Test
	public void getTopRankUser() {
		// Crear un usuario y ponerle el rango más alto. Ver después que recibimos ese
		// usuario al ejecutar el método.
		// Creación usuario prueba.
		User user4 = new User();
		user4.setActive(true);
		user4.setCreatedOn(new Timestamp(new Date().getTime()));
		user4.setEmail("rango@test.com");
		user4.setName("rango");
		user4.setPassword("password");
		user4.setRank(100000);
		user4.setUsername("rangoMásAlto");

		// Comprobado que todo está correcto, creamos el usuario.
		entityTransaction.begin();
		userService.create(user4);
		entityTransaction.commit();

		Assert.assertEquals(userService.getTopRankUser().getRank(), user4.getRank());
	}
}
