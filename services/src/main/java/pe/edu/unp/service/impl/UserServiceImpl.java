package pe.edu.unp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.unp.domain.User;
import pe.edu.unp.repository.UserRepository;
import pe.edu.unp.service.UserService;
import pe.edu.unp.util.Constants;

@Service
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super(repository);
    }

    @Override
    public User findUserByUsername(String username) {
        return repository.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setLostGames(0);
        user.setPlays(0);
        user.setPoints(Constants.START_POINTS);
        user.setWonGames(0);
        super.save(user);
    }

}
