package project.linus.service;

import org.springframework.stereotype.Service;
import project.linus.model.User;
import project.linus.util.encoder.PasswordEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();
    User loggedUser;
    PasswordEncoder encoder = new PasswordEncoder();

    public String addUser(User user) {
        for(User u : users){
            if(u.getUsername().equals(user.getUsername())){
                return "Este username já está sendo utilizado";
            }
            if(u.getEmail().equals(user.getEmail())){
                return "Este email já está cadastrado!";
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));
        users.add(user);
        return "Cadastrado com sucesso!";
    }

    public List<User> listUsers() {
        return users;
    }

    public Integer listUsersLength() {
        return users.size();
    }

    @Override
    public String login(String username, String password) {
        for (User user : users) {
            if(user.getUsername().equals(username)
            && encoder.verify(password, user.getPassword())){
                loggedUser = user;
                return "Logado com sucesso!";
            }
        }
        return "Username ou senha incorretos!";
    }

    @Override
    public String login(String username, String password, String adminKey) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && encoder.verify(password, user.getPassword())
                    && user.getAdminKey().equals(adminKey)) {
                loggedUser = user;
                return "Logado com sucesso!";
            }
        }
        return "Username ou senha incorretos!";
    }

    @Override
    public String logout() {
        if(loggedUser == null){
            return "Não é possível deslogar sem fazer um login :)";
        }
        loggedUser = null;
        return "Você foi deslogado! Aguardamos a sua volta!";
    }
}
