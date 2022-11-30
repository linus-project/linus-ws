package project.linus.service.login;

import org.hibernate.validator.constraints.NotBlank;
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
import project.linus.util.generic.PilhaObj;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoginService {
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    UserRepository userRepository;

    List<PilhaObj> listaPilha = new ArrayList<>();

    public User login(AdminLogin login) {

        PilhaObj p = new PilhaObj(3);
        listaPilha.add(p);

        Integer indice = listaPilha.size() - 1;

        User user = userRepository.findByUsername(login.getUsername());
        if (user != null) {
            boolean haveAdminKey = user.adminKey().equals(login.getAdminKey());
            boolean usernameIsValid = user.getUsername().equals(login.getUsername());
            boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

            if (haveAdminKey && usernameIsValid && passwordIsValid) {
                return user;
            } else {
                while (!listaPilha.get(indice).isEmpty()) {
                    listaPilha.get(indice).pop();
                }
            }
        }
        p.push(1);

        if (listaPilha.get(indice).isFull()) {
            user.setIsBlocked(1);
            userRepository.save(user);
        }

        throw new LoginException();
    }

    public User login(UserLogin login) {
        User user = userRepository.findByUsername(login.getUsername());
        if (user != null) {
            boolean usernameIsValid = user.getUsername().equals(login.getUsername());
            boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

            if (usernameIsValid && passwordIsValid) {
                return user;
            }
        }
        throw new LoginException();
    }

    public User login(UserLoginEmail login) {
        User user = userRepository.findByEmail(login.getEmail());

        boolean haveAdminKey = user.adminKey() == null;
        boolean emailIsValid = user.getEmail().equals(login.getEmail());
        boolean passwordIsValid = encoder.verify(login.getPassword(), user.getPassword());

        if (haveAdminKey && emailIsValid && passwordIsValid) {
            return user;
        }

        throw new LoginException();
    }

    public User login(AdminLoginEmail login) {
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
