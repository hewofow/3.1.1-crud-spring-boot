package dao;

import model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
    }

    @Override
    public void remove(User user) {
//        sessionFactory.getCurrentSession().delete(user);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(user);
        session.getTransaction().commit();
    }

    @Override
    public void update(User user) {
//        sessionFactory.getCurrentSession().saveOrUpdate(user);
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();
    }

    @Override
    public List<User> listUsers() {
        return sessionFactory.getCurrentSession().createQuery("FROM User", User.class).getResultList();
    }

    @PostConstruct
    public void initDataBase() {
        List<User> list = new ArrayList<>();
        list.add(new User("Vasya", "Ivanov", "1000000001"));
        list.add(new User("Petya", "Golovach", "2000000002"));
        list.add(new User("Bob", "Sponge", "3000000003"));
        list.add(new User("Johan", "Kek", "4000000004"));
        list.add(new User("Pepa", "Josefina", "5000000005"));

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        for (User user : list) {
            session.save(user);
        }
        session.getTransaction().commit();
    }
}
