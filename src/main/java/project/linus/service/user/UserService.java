package project.linus.service.user;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import project.linus.model.user.User;
import project.linus.util.encoder.PasswordEncoder;
import project.linus.util.exception.EmailException;
import project.linus.util.exception.UsernameException;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    List<User> users = new ArrayList<>();
    PasswordEncoder encoder = new PasswordEncoder();

    public User addUser(User user){
        for (User u : users) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new UsernameException();
            }
            if (u.getEmail().equals(user.getEmail())) {
                throw new EmailException();
            }
        }
        user.setPassword(encoder.encode(user.getPassword()));
        users.add(user);
        return user;
    }

    public List<User> listUsers() {
        return users;
    }

    public Integer listUsersLength() {
        return users.size();
    }
}
