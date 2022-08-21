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
    public void addUser(@RequestBody User user){
        userService.addUser(user);
    }

    @PostMapping("/user/common/login")
    public User login(@RequestBody Login login){
        if (!login.getAdminKey().equals("")){
            return userService.login(login.getUsername(), login.getPassword(), login.getAdminKey());
        }
        return userService.login(login.getUsername(), login.getPassword());
    }

    @GetMapping("/user/common/list")
    public List<User> listUsers(){
        return userService.listUsers();
    }

}
