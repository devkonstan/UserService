package pl.sda.service;

import org.springframework.stereotype.Service;
import pl.sda.model.LoginData;
import pl.sda.model.User;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    public void save(User user){
        throw new UnsupportedOperationException();
    }

    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    public void remove(String login){
        throw new UnsupportedOperationException();
    }

    public Optional<User> getByLogin(String login){
        throw new UnsupportedOperationException();
    }

    public List<User> getAll(){
        throw new UnsupportedOperationException();
    }

    public Optional<User> login(LoginData loginData){
        throw new UnsupportedOperationException();
    }
}
