package project.linus.model.exception;

import java.util.Date;

public class ExceptionMessage {
    private int status;
    private String error;
    private String message;
    private Date timestamp;

    public ExceptionMessage(int status, String error, String message, Date timestamp) {
        this.status = status;
        this.error = error;
        this.message = message;
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public Date getTimestamp() {
        return timestamp;
    }
}
