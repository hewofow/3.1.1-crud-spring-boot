package crud.services;

import crud.daos.UserDao;
import crud.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional
    @Override
    public void remove(long id) {
        userDao.remove(id);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(long id) { return userDao.getById(id); }

    @Transactional(readOnly = true)
    @Override
    public List<User> listUsers() {
        return userDao.listUsers();
    }
}
