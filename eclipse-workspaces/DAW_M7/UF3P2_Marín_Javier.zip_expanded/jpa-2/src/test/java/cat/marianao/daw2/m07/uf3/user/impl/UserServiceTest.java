package cat.marianao.daw2.m07.uf3.user.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.Context;
import javax.naming.NamingException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ArchivePaths;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import cat.marianao.daw2.m07.uf3.domain.User;
import cat.marianao.daw2.m07.uf3.service.UserService;
import cat.marianao.daw2.m07.uf3.service.impl.UserServiceImpl;
import cat.marianao.daw2.m07.uf3.user.mock.Mock;

@RunWith(Arquillian.class)
public class UserServiceTest {
	@Inject
	private UserService userService;

	@Deployment(testable = true)
	public static JavaArchive createTestableDeployment() {
		final JavaArchive jar = ShrinkWrap.create(JavaArchive.class, "example.jar")
				.addClasses(UserService.class, UserServiceImpl.class)
				.addAsManifestResource("META-INF/persistence-test.xml", "persistence.xml")
				.addAsManifestResource(EmptyAsset.INSTANCE, ArchivePaths.create("beans.xml"));
		return jar;
	}

	@Before
	public void setUp() throws NamingException {
		Context context = EJBContainer.createEJBContainer().getContext();
		userService = (UserService) context.lookup("java:global/classes/UserServiceImpl");
	}

	@Test
	public void findAllUsers() {

		User user0 = Mock.getUser0();
		User user1 = Mock.getUser1();

		userService.create(user0);
		userService.create(user1);

		List<User> users = userService.getAllUsers();
		Assert.assertEquals(2, users.size());

		// TODO - Make the needed changes to active the following lines
		// userService.remove(user0);
		// userService.remove(user1);
	}

	@Test
	public void createUser() throws SQLException {
		User user1 = new User();
		user1.setActive(true);
		user1.setCreatedOn(new Timestamp(new Date().getTime()));
		user1.setEmail("test1@test.com");
		user1.setName("Joe");
		user1.setPassword("password");
		user1.setRank(100);
		user1.setUsername("joeTest");

		userService.create(user1);

		System.out.println(userService.getAllUsers());
	}

	@Test
	public void findActiveUsers() throws SQLException {
		// Recoger los usuarios activos de la base de datos que deben ser 2.
		Assert.assertEquals(userService.findActiveUsers().size(), 2);
	}
}
