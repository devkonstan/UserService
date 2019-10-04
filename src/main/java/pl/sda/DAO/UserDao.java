package pl.sda.DAO;

import pl.sda.model.LoginData;
import pl.sda.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserDao {
    protected EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;
    // musi pokrywac sie z nazwa w persistence.xml
    public UserDao() {
        entityManagerFactory = Persistence.createEntityManagerFactory("userManager");
    }

    public void openSessionWithTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public void closeSessionWithTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public List<User> getAll() {
//        return entityManager.createQuery("select * FROM User", User.class).getResultList();
        return entityManager.createQuery("FROM User").getResultList();
    }

    public Optional<User> getByLogin(String login) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.login =:login", User.class)
                .setParameter("login", login)
                .getSingleResult();

        return Optional.ofNullable(user);
    }


    public void save(User user) {
        entityManager.merge(user);
    }

    public void update(User user) {
        entityManager.merge(user);
    }

    public void delete(User user) {
        entityManager.remove(user);
    }

    public void login(String login, String password) {
        entityManager.createQuery("SELECT u FROM User u WHERE u.login =:login and u.password =:password", User.class)
                .setParameter("login", login)
                .setParameter("password", password)
                .getSingleResult();
    }
}
