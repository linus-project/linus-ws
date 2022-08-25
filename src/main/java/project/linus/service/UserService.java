package project.linus.service;

import project.linus.model.User;

import java.util.List;

public interface UserService {

    public String login(String username, String password);

    public String addUser(User user);

    public List<User> listUsers();

    public Integer listUsersLength();

    public String login(String username, String password, String adminKey);

    public String logout();
}
