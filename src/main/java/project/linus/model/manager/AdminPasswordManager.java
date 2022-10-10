package project.linus.model.manager;

public class AdminPasswordManager extends PasswordManager{
    private String adminKey;

    public AdminPasswordManager(String username, String password, String newPassword, String adminKey) {
        super(username, password, newPassword);
        this.adminKey = adminKey;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
