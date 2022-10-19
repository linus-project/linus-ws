package project.linus.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.login.AdminLogin;
import project.linus.model.login.UserLogin;
import project.linus.model.user.User;
import project.linus.model.user.AdminPasswordManager;
import project.linus.model.user.UserPasswordManager;
import project.linus.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        return ResponseEntity.ok(userService.addUser(user));
    }

    @GetMapping("/list")
    public ResponseEntity<List<User>> listUsers(@RequestHeader String secretKey) {
        if(secretKey.equals("grupo6")){
            return ResponseEntity.ok(userService.listUsers());
        }
        return ResponseEntity.status(403).build();
    }

    @GetMapping("/count")
    public ResponseEntity<Long> countUsers(@RequestHeader String secretKey) {
        if(secretKey.equals("grupo6")){
            return ResponseEntity.ok(userService.countUsers());
        }
        return ResponseEntity.status(403).build();
    }

    @DeleteMapping
    public ResponseEntity<User> deleteUser(@RequestBody UserLogin login) {
        return ResponseEntity.ok(userService.deleteUser(login));
    }

    @DeleteMapping("/admin")
    public ResponseEntity<User> deleteUserAdmin(@RequestBody AdminLogin login) {
        return ResponseEntity.ok(userService.deleteUserAdmin(login));
    }

    @PutMapping
    public ResponseEntity<User> changePassword(@RequestBody UserPasswordManager login){
        return ResponseEntity.ok(userService.changePassword(login));
    }

    @PutMapping("/admin")
    public ResponseEntity<User> changePasswordAdmin(@RequestBody AdminPasswordManager login){
        return ResponseEntity.ok(userService.changePasswordAdmin(login));
    }

}
