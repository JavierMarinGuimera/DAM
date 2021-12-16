package cat.institutmarianao.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import cat.institutmarianao.domain.Role;
import cat.institutmarianao.domain.User;
import cat.institutmarianao.repository.UserRepository;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {

	private static final List<User> users = new ArrayList<>();

	static {
		users.add(createUser("ADMIN", "", "admin", "xxxx", "ROLE_ADMIN"));
		users.add(createUser("John", "Doe", "john", "passwd", "ROLE_USER"));
		users.add(createUser("Jane", "Doe", "jane", "passwd", "ROLE_USER"));
		users.add(createUser("Javier", "Marin", "javi", "1234", "ROLE_USER"));
	}

	@Override
	public User get(String username) {
		for (User user : users) {
			if (user != null && user.getUsername() != null && user.getUsername().equals(username)) {
				return user;
			}
		}
		return null;
	}

	private static User createUser(String firstName, String lastName, String username, String password, String role) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setUsername(username);
		user.setPassword(password);
		List<Role> authorities = new ArrayList<>();
		authorities.add(new Role(role));
		user.setAuthorities(authorities);
		return user;
	}
}
