package crud.daos;

import crud.models.User;
import crud.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AppListener {
    private UserService userService;
    List<User> list;

    @Autowired
    public AppListener(UserService userService) {
        this.userService = userService;
        initList();
    }

    public void initList() {
        list = new ArrayList<>();
        list.add(new User("Vasya", "Ivanov", "1000000001"));
        list.add(new User("Petya", "Golovach", "2000000002"));
        list.add(new User("Bob", "Sponge", "3000000003"));
        list.add(new User("Johan", "Kek", "4000000004"));
        list.add(new User("Pepa", "Josefina", "5000000005"));

        for (User user : list) {
            this.userService.add(user);
        }
    }
}

//@Component
//public class AppListener implements ApplicationListener<ContextRefreshedEvent> {
//    private UserService userService;
//
//    @Autowired
//    public AppListener(UserService userService) {
//        this.userService = userService;
//    }
//
//    @Transactional
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        List<User> list = new ArrayList<>();
//        list.add(new User("Vasya", "Ivanov", "1000000001"));
//        list.add(new User("Petya", "Golovach", "2000000002"));
//        list.add(new User("Bob", "Sponge", "3000000003"));
//        list.add(new User("Johan", "Kek", "4000000004"));
//        list.add(new User("Pepa", "Josefina", "5000000005"));
//
//        for (User user : list) {
//            userService.add(user);
//        }
//    }
//}
