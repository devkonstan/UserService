package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.DAO.UserDao;
import pl.sda.model.LoginData;
import pl.sda.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private UserDao userDAO = new UserDao();


    public void save(User user) {
        userDAO.openSessionWithTransaction();
        userDAO.save(user);
        userDAO.closeSessionWithTransaction();
    }

    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    public void remove(String login) { //zapytanie sql
            userDAO.openSessionWithTransaction();
            Optional<User> user = userDAO.getByLogin(login);
            user.ifPresent(userDAO::delete);
            userDAO.closeSessionWithTransaction();
        }


    public Optional<User> getByLogin(String login) {
        userDAO.openSessionWithTransaction();
        Optional<User> user = userDAO.getByLogin(login);
        userDAO.closeSessionWithTransaction();

        return user;
    }

    public List<User> getAll() {

        userDAO.openSessionWithTransaction();
        List<User> users = userDAO.getAll();
        userDAO.closeSessionWithTransaction();

        return users;
    }

    public Optional<User> login(LoginData loginData) {
        throw new UnsupportedOperationException();
    }

}
