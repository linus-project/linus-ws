package project.linus.controller.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.login.*;
import project.linus.model.user.User;
import project.linus.service.login.LoginService;

import java.util.logging.Logger;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    LoginService loginService;

    Logger logger = Logger.getLogger(LoginController.class.getName());

    @PostMapping("/username")
    public ResponseEntity<User> login(@RequestBody UserLogin login) {
        logger.info("Class: LoginController - Method: login");
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/admin/username")
    public ResponseEntity<User> login(@RequestBody AdminLogin login) {
        logger.info("Class: LoginController - Method: login");
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/email")
    public ResponseEntity<User> login(@RequestBody UserLoginEmail login){
        logger.info("Class: LoginController - Method: login");
        return ResponseEntity.ok(loginService.login(login));
    }

    @PostMapping("/admin/email")
    public ResponseEntity<User> login(@RequestBody AdminLoginEmail login){
        logger.info("Class: LoginController - Method: login");
        return ResponseEntity.ok(loginService.login(login));
    }


}
