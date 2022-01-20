package cat.marianao.daw2.m07.uf3.service;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import cat.marianao.daw2.m07.uf3.domain.User;

@Local
public interface UserService {
	List<User> getAllUsers();

	void create(User user);

	void edit(User user);

	void remove(User user);

	List<User> findActiveUsers() throws SQLException;

	User findUserByUsername(String username);

	User findUserByEmail(String mail);

	User createUser(User user);

	User setUserInactive(User user);
}
