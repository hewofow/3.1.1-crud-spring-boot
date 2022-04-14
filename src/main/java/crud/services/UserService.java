package crud.services;

import crud.models.User;

import java.util.List;

public interface UserService {
    void add(User user);
    void remove(long id);
    void update(User user);
    User getById(long id);
    List<User> listUsers();
}
