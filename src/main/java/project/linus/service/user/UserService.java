package project.linus.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.login.AdminLogin;
import project.linus.model.login.UserLogin;
import project.linus.model.user.User;
import project.linus.model.user.AdminPasswordManager;
import project.linus.model.user.UserPasswordManager;
import project.linus.repository.user.UserRepository;
import project.linus.util.encoder.PasswordEncoder;
import project.linus.util.exception.EmailException;
import project.linus.util.exception.UsernameException;
import project.linus.service.login.LoginService;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    LoginService loginService;

    public User addUser(User user) {
        if (!verifyIfUserExists(user)) {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
        }
        return user;
    }

    public boolean verifyIfUserExists(User user) {
        for (User u : listUsers()) {
            if (u.getUsername().equals(user.getUsername())) {
                throw new UsernameException();
            } else if (u.getEmail().equals(user.getEmail())) {
                throw new EmailException();
            }
        }
        return false;
    }

    public List<User> listUsers() {
        return userRepository.findAll();
    }

    public Long countUsers() {
        return userRepository.count();
    }

    public User deleteUser(UserLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        userRepository.delete(loginService.login(login));
        return user;
    }

    public User deleteUserAdmin(AdminLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        userRepository.delete(loginService.login(login));
        return user;
    }

    public User changePassword(UserPasswordManager login) {
        User user = userRepository.findByUsername(login.getUsername());
        user.setPassword(encoder.encode(login.getNewPassword()));
        userRepository.save(user);
        return user;
    }

    public User changePasswordAdmin(AdminPasswordManager login) {
        User user = userRepository.findByUsername(login.getUsername());
        user.setPassword(encoder.encode(login.getNewPassword()));
        userRepository.save(user);
        return user;
    }

}
