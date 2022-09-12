package project.linus.util.encoder;

import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

public class PasswordEncoder {
    Pbkdf2PasswordEncoder encoder = new Pbkdf2PasswordEncoder();

    public String encode(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String password, String hash) {
        return encoder.matches(password, hash);
    }
}
