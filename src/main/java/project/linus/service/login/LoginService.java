package project.linus.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.login.AdminLogin;
import project.linus.model.login.AdminLoginEmail;
import project.linus.model.login.UserLoginEmail;
import project.linus.model.user.User;
import project.linus.model.login.UserLogin;
import project.linus.repository.user.UserRepository;
import project.linus.util.encoder.PasswordEncoder;
import project.linus.util.exception.LoginException;

@Service
public class LoginService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;

    public User login(AdminLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        if(user != null) {
            boolean haveAdminKey = user.adminKey().equals(login.getAdminKey());
            boolean usernameIsValid = user.getUsername().equals(login.getUsername());
            boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

            if (haveAdminKey && usernameIsValid && passwordIsValid) {
                return user;
            }
        }
        throw new LoginException();
    }

    public User login(UserLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        if(user != null) {
            boolean haveAdminKey = user.adminKey() == null;
            boolean usernameIsValid = user.getUsername().equals(login.getUsername());
            boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

            if (haveAdminKey && usernameIsValid && passwordIsValid) {
                return user;
            }
        }
        throw new LoginException();
    }

    public User login(UserLoginEmail login){
        User user = userRepository.findByEmail(login.getEmail());

        boolean haveAdminKey = user.adminKey() == null;
        boolean emailIsValid = user.getEmail().equals(login.getEmail());
        boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

        if (haveAdminKey && emailIsValid && passwordIsValid) {
            return user;
        }

        throw new LoginException();
    }

    public User login(AdminLoginEmail login){
        User user = userRepository.findByEmail(login.getEmail());

        boolean haveAdminKey = user.adminKey().equals(login.getAdminKey());
        boolean emailIsValid = user.getEmail().equals(login.getEmail());
        boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

        if (haveAdminKey && emailIsValid && passwordIsValid) {
            return user;
        }

        throw new LoginException();
    }



}
