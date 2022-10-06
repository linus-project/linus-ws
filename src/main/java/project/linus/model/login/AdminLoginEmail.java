package project.linus.model.login;

public class AdminLoginEmail extends UserLoginEmail{

    private String adminKey;

    public AdminLoginEmail(String email, String password, String adminKey) {
        super(email, password);
        this.adminKey = adminKey;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }
}
