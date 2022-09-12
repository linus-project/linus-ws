package project.linus.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Usuário ou senha incorretos!")
public class LoginException extends RuntimeException{
}
