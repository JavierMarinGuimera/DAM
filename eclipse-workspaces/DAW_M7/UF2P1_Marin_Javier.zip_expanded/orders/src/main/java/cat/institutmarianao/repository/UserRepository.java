package cat.institutmarianao.repository;

import cat.institutmarianao.domain.User;

public interface UserRepository {
	User get(String username);
}
