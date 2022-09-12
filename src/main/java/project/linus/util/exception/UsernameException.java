package project.linus.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.BAD_REQUEST, reason = "Esse usuário já existe!")
public class UsernameException extends RuntimeException{
}
