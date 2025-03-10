package project.linus.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.controller.login.LoginController;
import project.linus.model.login.AdminLogin;
import project.linus.model.login.UserLogin;
import project.linus.model.user.User;
import project.linus.model.user.AdminManager;
import project.linus.model.user.UserManager;
import project.linus.service.user.UserService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    Logger logger = Logger.getLogger(UserController.class.getName());

    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        logger.info("Class: UserController - Method: addUser");
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

    @PostMapping("/edit")
    public ResponseEntity<User> changeUserInfo(@RequestBody UserManager login){
        return ResponseEntity.ok(userService.changeUserInfo(login));
    }

    @PostMapping("/admin/edit")
    public ResponseEntity<User> changeAdminInfo(@RequestBody AdminManager login){
        return ResponseEntity.ok(userService.changeAdminInfo(login));
    }

    @GetMapping("/export")
    public ResponseEntity<String> exportUsers(@RequestParam Integer listSize, @RequestParam String fileTitle) {
        return ResponseEntity.ok(userService.exportUsers(listSize, fileTitle));
    }

}
