package tutorial.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import tutorial.springboot.jpa.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByRole(String role);

	List<User> findByName(String name);

	List<User> findByRoleAndName(String role, String name);

	List<User> findByRoleOrName(String role, String name);

	@Query(value = "select * from User order by name", nativeQuery = true)
	List<User> findDistinctRoles();
}
