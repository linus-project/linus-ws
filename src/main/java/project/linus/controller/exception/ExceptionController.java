package project.linus.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import project.linus.model.exception.ExceptionMessage;
import project.linus.util.exception.*;

import java.util.Date;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(UsernameException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleUsernameException() {
        return new ExceptionMessage(400, "Bad Request", "Esse usuário já existe!", new Date());
    }

    @ExceptionHandler(EmailException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleEmailException() {
        return new ExceptionMessage(400, "Bad Request", "Este email já está cadastrado!", new Date());
    }

    @ExceptionHandler(LoginException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ExceptionMessage handleLoginException() {
        return new ExceptionMessage(403, "Forbidden", "Usuário ou senha incorretos!", new Date());
    }

    @ExceptionHandler(GenericException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleContentException() {
        return new ExceptionMessage(400, "Bad Request", "Não é possível realizar a operação!", new Date());
    }
}
