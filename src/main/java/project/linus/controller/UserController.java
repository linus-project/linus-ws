package project.linus.controller;

import org.springframework.web.bind.annotation.*;
import project.linus.model.Login;
import project.linus.model.User;
import project.linus.service.UserServiceImpl;
import java.util.List;

@RestController
@RequestMapping("/platform")
public class UserController {

    UserServiceImpl userService = new UserServiceImpl();

    @PostMapping("/user/add")
    public String addUser(@RequestBody User user){
        return userService.addUser(user);
    }

    @PostMapping("/user/login")
    public String login(@RequestBody Login login){
        if (!login.getAdminKey().equals("")){
            return userService.login(login.getUsername(), login.getPassword(), login.getAdminKey());
        }
        return userService.login(login.getUsername(), login.getPassword());
    }

    @GetMapping("/user/logout")
    public String logout(){
        return userService.logout();
    }

    @GetMapping("/user/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

}
