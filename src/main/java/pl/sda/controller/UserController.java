package pl.sda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.model.LoginData;
import pl.sda.model.User;
import pl.sda.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

/**
 * Prosty serwis udostępniający kilka metod do obsługi użytkowników systemu.
 * Na tę chwilę nie przejmuj się implementacją bo tym zajmiemy się w przyszłości.
 * Póki co postaraj się zaimplementować klasę UserService tak aby można było w pełni korzystać z funkcjonalności serwisu.
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index() {
        return "Witaj, to jest serwis do obsługi użytkowników. Serwis udostępnia poniższe metody: \n" +
                "GET: localhost:8080/users - zwraca listę użytkowników.\n" +
                "GET: localhost:8080/user/login - zwraca użytkownika po loginie, np.: localhost:8080/user/jkowalski.\n" +
                "DELETE: localhost:8080/user/login - usuwa użytkownika po loginie, np.: localhost:8080/user/jkowalski.\n" +
                "POST: localhost:8080/user - Rejestruje użytkownika (pamiętaj o dodaniu body). \n" +
                "PUT: localhost:8080/user - Aktualizuje użytkownika (pamiętaj o dodaniu body). \n" +
                "POST: localhost:8080/user/login - pozwala zalogować się użytkownikowi (pamiętaj o dodaniu body).\n\n\n" +
                "" +
                "Przekazywanie parametrów odbywa się przez utworzenia JSONa, np. logowanie: \n" +
                "POST: localhost:8080/user/login \n" +
                "BODY:\n" +
                "{\n" +
                "    \"login\" : \"jkowalski\", \n" +
                "    \"password\" : \"psw212\" \n" +
                "}";
    }

    @PostMapping("/user")
    public ResponseEntity register(@RequestBody User user) {
        userService.save(user);

        return ResponseEntity.created(URI.create("http://localhost:8080/user/" + user.getLogin())).build();
    }

    @PutMapping("/user")
    public ResponseEntity update(@RequestBody User user) {
        userService.update(user);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/users")
    public ResponseEntity getAll() {
        List<User> users = userService.getAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/user/{login}")
    public ResponseEntity getByLogin(@PathVariable String login) {
        Optional<User> userOpt = userService.getByLogin(login);

        return userOpt.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/user/{login}")
    public ResponseEntity deleteByLogin(@PathVariable String login) {
        Optional<User> userOpt = userService.getByLogin(login);

        return userOpt.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/user/login")
    public ResponseEntity login(@RequestBody LoginData loginData) {
        Optional<User> loggedUser = userService.login(loginData);

        return loggedUser.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
