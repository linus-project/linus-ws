package project.linus.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.controller.user.UserController;
import project.linus.model.login.AdminLogin;
import project.linus.model.user.User;
import project.linus.model.login.UserLogin;
import project.linus.service.login.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/user")
    public ResponseEntity<User> login(@RequestBody UserLogin login) {
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/admin")
    public ResponseEntity<User> login(@RequestBody AdminLogin login) {
        return ResponseEntity.ok(loginService.login(login));
    }
}
