package project.linus.service;

import org.springframework.stereotype.Service;
import project.linus.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> listUsers() {
        return users;
    }

    public Integer listUsersLength() {
        return users.size();
    }

    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getAdminKey().equals("")) {
                return user;
            }
        }
        return null;
    }

    @Override
    public User login(String username, String password, String adminKey) {
        for (User user : users) {
            if (user.getUsername().equals(username)
                    && user.getPassword().equals(password)
                    && user.getAdminKey().equals(adminKey)) {
                return user;
            }
        }
        return null;
    }

}
