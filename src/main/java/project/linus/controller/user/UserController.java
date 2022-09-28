package project.linus.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.user.User;
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
}
