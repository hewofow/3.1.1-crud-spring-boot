package crud.repositories;

import crud.models.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InitialiyationBeanImpl implements InitializingBean {
    private UserRepository userRepository;

    @Autowired
    public InitialiyationBeanImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        userRepository.save(new User("Vasya", "Ivanov", "1000000001"));
        userRepository.save(new User("Petya", "Golovach", "2000000002"));
        userRepository.save(new User("Bob", "Sponge", "3000000003"));
        userRepository.save(new User("Johan", "Kek", "4000000004"));
        userRepository.save(new User("Pepa", "Josefina", "5000000005"));
    }
}
