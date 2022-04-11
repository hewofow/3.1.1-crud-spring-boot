package crud.daos;

import crud.models.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
//    public void add(User user) {
//        entityManager.getCurrentSession().save(user);
//    }
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void remove(long id) {
//        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
//                .setParameter("id", id)
//                .executeUpdate();
        entityManager.remove(entityManager.find(User.class, id));
    }

    @Override
    public User getById(long id) {
//        return entityManager.createQuery("SELECT u FROM User u WHERE u.id = :id", User.class)
//                .setParameter("id", id)
//                .getSingleResult();
        return entityManager.find(User.class, id);
    }

    @Override
    public void update(User user) {
//        entityManager.createQuery("UPDATE User SET firstName = :firstName, lastName = :lastName, " +
//                        "phoneNumber = :phoneNumber WHERE id = :id")
//                .setParameter("firstName", user.getFirstName())
//                .setParameter("lastName", user.getLastName())
//                .setParameter("phoneNumber", user.getPhoneNumber())
//                .setParameter("id", user.getId())
//                .executeUpdate();
        entityManager.merge(user);
    }

    @Override
    public List<User> listUsers() {
        return entityManager.createQuery("FROM User", User.class).getResultList();
    }

//    @Transactional
//    public void initDataBase() {
//        List<User> list = new ArrayList<>();
//        list.add(new User("Vasya", "Ivanov", "1000000001"));
//        list.add(new User("Petya", "Golovach", "2000000002"));
//        list.add(new User("Bob", "Sponge", "3000000003"));
//        list.add(new User("Johan", "Kek", "4000000004"));
//        list.add(new User("Pepa", "Josefina", "5000000005"));
//
//        for (User user : list) {
//            entityManager.persist(user);
//        }
//    }
}
