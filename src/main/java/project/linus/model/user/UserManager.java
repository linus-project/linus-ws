package project.linus.model.user;

import project.linus.util.manager.Manager;

import java.time.LocalDate;

public class UserManager extends Manager {
    private String name;
    private String email;
    private String newUsername;
    private String phoneNumber;
    private LocalDate bornDate;
    private String newPassword;

    public UserManager(String username, String password, String name, String email, String newUsername, String phoneNumber, LocalDate bornDate, String newPassword) {
        super(username, password);
        this.name = name;
        this.email = email;
        this.newUsername = newUsername;
        this.phoneNumber = phoneNumber;
        this.bornDate = bornDate;
        this.newPassword = newPassword;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getNewUsername() {
        return newUsername;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
