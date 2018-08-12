package pe.edu.unp.repository;

import org.springframework.data.repository.CrudRepository;
import pe.edu.unp.domain.User;

public interface UserRepository extends CrudRepository<User, String> {

    public User findUserByUsername(String username);

}
