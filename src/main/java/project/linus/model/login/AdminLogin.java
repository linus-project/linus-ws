package project.linus.model.login;

public class AdminLogin extends Login {
    private String adminKey;

    public AdminLogin(String username, String password, String adminKey) {
        super(username, password);
        this.adminKey = adminKey;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
