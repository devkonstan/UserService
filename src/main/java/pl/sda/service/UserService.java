package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.DAO.UserDao;
import pl.sda.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDao = new UserDao();

    public void save(User user) {
        userDao.openSessionWithTransaction();
        userDao.save(user);
        userDao.closeSessionWithTransaction();
    }

    public void remove(String login) {
        userDao.openSessionWithTransaction();
        Optional<User> user = userDao.getByLogin(login);
        user.ifPresent(userDao::delete);
        userDao.closeSessionWithTransaction();
    }

    public Optional<User> getByLogin(String login) {
        userDao.openSessionWithTransaction();
        Optional<User> user = userDao.getByLogin(login);
        userDao.closeSessionWithTransaction();

        return user;
    }

    public List<User> getAll() {
        userDao.openSessionWithTransaction();
        List<User> users = userDao.getAll();
        userDao.closeSessionWithTransaction();

        return users;
    }

    public void update(User user) {
        userDao.openSessionWithTransaction();
        userDao.update(user);
        userDao.closeSessionWithTransaction();

    }

    public Optional<User> login(String login, String password) {
        userDao.openSessionWithTransaction();
        userDao.login(login, password);
        userDao.closeSessionWithTransaction();

        return null;
    }

}
