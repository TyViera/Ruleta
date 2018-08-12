package pe.edu.unp.service;

import pe.edu.unp.domain.User;

public interface UserService extends BaseService<User, String> {

    public User findUserByUsername(String username);

}
