package crud.dao;

import crud.model.User;
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
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void remove(long id) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }

    @Override
    public User getById(long id) {
        return sessionFactory.getCurrentSession()
                .createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession()
                .createQuery("UPDATE User SET firstName = :firstName, lastName = :lastName, " +
                        "phoneNumber = :phoneNumber WHERE id = :id")
                .setParameter("firstName", user.getFirstName())
                .setParameter("lastName", user.getLastName())
                .setParameter("phoneNumber", user.getPhoneNumber())
                .setParameter("id", user.getId())
                .executeUpdate();
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
