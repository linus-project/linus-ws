package project.linus.service;

import project.linus.model.User;

import java.util.List;

public interface UserService {

    public User login(String username, String password);

    public void addUser(User user);

    public List<User> listUsers();

    public Integer listUsersLength();

    User login(String username, String password, String adminKey);
}
