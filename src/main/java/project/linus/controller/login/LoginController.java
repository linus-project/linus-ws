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
@RequestMapping
public class LoginController {
    @Autowired
    LoginService loginService;
    @Autowired
    UserController userController;

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody UserLogin login) {
        return ResponseEntity.ok(loginService.login(login, userController));
    }

    @PostMapping("/admin")
    public ResponseEntity<User> login(@RequestBody AdminLogin login) {
        return ResponseEntity.ok(loginService.login(login, userController));
    }

    @GetMapping("/logout")
    public ResponseEntity<String> logout() {
        return ResponseEntity.ok(loginService.logout());
    }
}
