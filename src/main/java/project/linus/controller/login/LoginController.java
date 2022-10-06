package project.linus.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.controller.user.UserController;
import project.linus.model.login.*;
import project.linus.model.user.User;
import project.linus.service.login.LoginService;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    @PostMapping("/username")
    public ResponseEntity<User> login(@RequestBody UserLogin login) {
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/admin/username")
    public ResponseEntity<User> login(@RequestBody AdminLogin login) {
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/email")
    public ResponseEntity<User> login(@RequestBody UserLoginEmail login){
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/admin/email")
    public ResponseEntity<User> login(@RequestBody AdminLoginEmail login){
        return ResponseEntity.ok(loginService.login(login));
    }


}
