package crud.dao;

import crud.model.User;

import java.util.List;

public interface UserDao {
    public void add(User user);
    public void remove(long id);
    public void update(User user);
    public User getById(long id);
    public List<User> listUsers();
}
