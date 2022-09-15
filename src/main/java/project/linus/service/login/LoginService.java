package project.linus.service.login;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import project.linus.controller.user.UserController;
import project.linus.model.login.AdminLogin;
import project.linus.model.user.User;
import project.linus.model.login.UserLogin;
import project.linus.util.encoder.PasswordEncoder;
import project.linus.util.exception.LoginException;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    PasswordEncoder encoder = new PasswordEncoder();
    List<User> users = new ArrayList<>();
    User loggedUser;

    public User login(AdminLogin login, @NotNull UserController userController) {
        users = userController.listUsers().getBody();
        for (User user : users) {
            if (user.getUsername().equals(login.getUsername())
                    && encoder.verify(login.getPassword(), user.getPassword())
                    && user.adminKey().equals(login.getAdminKey())) {
                loggedUser = user;
                return user;
            }
        }
        throw new LoginException();
    }

    public User login(UserLogin login, @NotNull UserController userController) {
        users = userController.listUsers().getBody();
        for (User user : users) {
            if (user.getUsername().equals(login.getUsername())
                    && encoder.verify(login.getPassword(), user.getPassword())) {
                loggedUser = user;
                return user;
            }
        }
        throw new LoginException();
    }

    public String logout() {
        if (loggedUser == null) {
            return "Não é possível deslogar sem fazer um login :)";
        }
        loggedUser = null;
        return "Você foi deslogado! Aguardamos a sua volta!";
    }
}
