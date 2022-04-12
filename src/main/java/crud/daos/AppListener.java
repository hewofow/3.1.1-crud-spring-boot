package crud.daos;

import crud.models.User;
import crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppListener {

    @Autowired
    public AppListener(UserService userService) {
        userService.add(new User("Vasya", "Ivanov", "1000000001"));
        userService.add(new User("Petya", "Golovach", "2000000002"));
        userService.add(new User("Bob", "Sponge", "3000000003"));
        userService.add(new User("Johan", "Kek", "4000000004"));
        userService.add(new User("Pepa", "Josefina", "5000000005"));
    }
}
