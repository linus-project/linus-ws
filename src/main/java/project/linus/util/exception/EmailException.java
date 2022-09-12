package project.linus.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason = "Esse email já está cadastrado!")
public class EmailException extends RuntimeException{
}
