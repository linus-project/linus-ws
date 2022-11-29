package project.linus.model.user;

import java.time.LocalDate;

public class AdminManager extends UserManager {
    private String adminKey;

    public AdminManager(String username, String password, String name, String email, String newUsername, String phoneNumber, LocalDate bornDate, String newPassword, String adminKey) {
        super(username, password, name, email, newUsername, phoneNumber, bornDate, newPassword);
        this.adminKey = adminKey;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
