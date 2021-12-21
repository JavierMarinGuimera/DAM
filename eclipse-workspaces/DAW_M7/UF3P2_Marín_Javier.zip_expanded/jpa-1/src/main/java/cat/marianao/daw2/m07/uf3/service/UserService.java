package cat.marianao.daw2.m07.uf3.service;

import java.sql.SQLException;
import java.util.List;

import cat.marianao.daw2.m07.uf3.domain.User;

public interface UserService {
	public void create(User user);

	public User edit(User user);

	public void remove(User user);

	public User findUserByUsername(String username);

	public List<User> findActiveUsers() throws SQLException;

	public User getTopRankUser();
}
