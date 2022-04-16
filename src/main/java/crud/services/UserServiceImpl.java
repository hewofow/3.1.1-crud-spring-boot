package crud.services;

import crud.models.User;
import crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) { userRepository.save(user); }

    @Override
    public void remove(long id) { userRepository.deleteById(id); }

    @Override
    public void update(User user) { userRepository.save(user); }

    @Override
    public User getById(long id) { return userRepository.findById(id); }

    @Override
    public List<User> listUsers() { return userRepository.findAll(); }
}
