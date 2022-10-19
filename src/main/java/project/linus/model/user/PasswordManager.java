package project.linus.model.user;

import project.linus.util.manager.Manager;

public abstract class PasswordManager extends Manager {
    private String newPassword;

    public PasswordManager(String username, String password, String newPassword) {
        super(username, password);
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
