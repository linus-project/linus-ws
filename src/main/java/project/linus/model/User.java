package project.linus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class User {
    private String name;
    private String username;
    private String password;
    private String email;
    private Integer birthday;
    private Integer level;
    @JsonIgnore
    private String adminKey;

    public User(String name, String username, String password, String email, Integer birthday, Integer level) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.birthday = birthday;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getBirthday() {
        return birthday;
    }

    public void setBirthday(Integer birthday) {
        this.birthday = birthday;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", birthday=" + birthday +
                ", level=" + level +
                '}';
    }
}
