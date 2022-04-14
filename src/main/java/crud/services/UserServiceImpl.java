package crud.services;

import crud.models.User;
import crud.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    @Override
    public void add(User user) { userRepository.save(user); }

    @Transactional
    @Override
    public void remove(long id) { userRepository.deleteById(id); }

    @Transactional
    @Override
    public void update(User user) { userRepository.save(user); }

    @Transactional(readOnly = true)
    @Override
    public User getById(long id) { return userRepository.findById(id); }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() { return userRepository.findAll(); }

    @PostConstruct
    @Transactional
    void initDataBase() {
        userRepository.save(new User("Vasya", "Ivanov", "1000000001"));
        userRepository.save(new User("Petya", "Golovach", "2000000002"));
        userRepository.save(new User("Bob", "Sponge", "3000000003"));
        userRepository.save(new User("Johan", "Kek", "4000000004"));
        userRepository.save(new User("Pepa", "Josefina", "5000000005"));
    }
}
