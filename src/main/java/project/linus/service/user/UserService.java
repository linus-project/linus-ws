package project.linus.service.user;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.content.Content;
import project.linus.model.login.AdminLogin;
import project.linus.model.login.UserLogin;
import project.linus.model.user.User;
import project.linus.model.user.AdminPasswordManager;
import project.linus.model.user.UserPasswordManager;
import project.linus.repository.user.UserRepository;
import project.linus.service.content.ContentService;
import project.linus.util.encoder.PasswordEncoder;
import project.linus.util.exception.EmailException;
import project.linus.util.exception.UsernameException;
import project.linus.service.login.LoginService;
import project.linus.util.generic.ObjectList;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.FormatterClosedException;
import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    LoginService loginService;

    private final Logger logger = LoggerFactory.logger(ContentService.class);

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

    public ObjectList<User> exportUsers(Integer listSize) {

        FileWriter file = null;
        Formatter formatter = null;
        String fileTitle = "users.txt";

        ObjectList<User> userList = new ObjectList(listSize);

        int index = 0;

        for (User user : userRepository.getUser()) {
            userList.add(user);
            if (index == listSize - 1) break;
            index++;
        }

        try {
            file = new FileWriter(fileTitle);
            formatter = new Formatter(file);
        } catch (IOException error) {
            logger.info("[ERROR] - exportUser: " + error);
        }

        try {
            String header = "00USUARIO20222";
            header += LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            header += "01";
            formatter.format(header + "\n");

            String body;
            for (index = 0; index < userList.getSize(); index++) {
                User user = userList.getElement(index);
                body = "03";
                body += String.format("%-5.5s", user.getIdUser());
                body += String.format("%-20.20s", user.getName());
                body += String.format("%-20.20s", user.getUsername());
                body += String.format("%-35.35s", user.getEmail());
                body += String.format("%-6.6s", user.getGenre());
                body += String.format("%-12.12s", user.getBornDate());
                body += String.format("%-12.12s", user.getPhoneNumber());
                body += String.format("%02d", user.getFkLevel());
                formatter.format(body + "\n");
            }

            String trailer = "01";
            trailer += String.format("%010d", index);
            formatter.format(trailer + "\n");

        } catch (FormatterClosedException error) {
            logger.info("[ERROR] - exportUser: " + error);
        } finally {
            formatter.close();
            try {
                file.close();
            } catch (IOException error) {
                logger.info("[ERROR] - exportUser: " + error);
            }
        }
        logger.info("The file " + fileTitle + " has been exported successfully!");
        return userList;
    }
}
