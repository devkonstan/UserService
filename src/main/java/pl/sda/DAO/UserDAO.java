package pl.sda.DAO;

import pl.sda.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Optional;

public class UserDAO {
    protected EntityManager entityManager;
    private EntityManagerFactory entityManagerFactory;

    public UserDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("entityManager");
    }

    public void openSessionWithTransaction() {
        entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
    }

    public void closeSessionWithTransaction() {
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void save(User user) {
        entityManager.merge(user);
    }

    public List<User> getAll() {
        return entityManager.createQuery("FROM User", User.class).getResultList();

    }

    public Optional<User> getByLogin(String login) {
        User user = entityManager.createQuery("SELECT u FROM User u WHERE u.login =:login", User.class)
                .setParameter("login", login)
                .getSingleResult();

        return Optional.ofNullable(user);
    }

    public void delete(User user) {

        entityManager.remove(user);
    }
}
