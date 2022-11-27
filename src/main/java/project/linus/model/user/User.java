package project.linus.model.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import project.linus.model.news.News;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUser;
    private String name;

    private String username;

    @Email
    private String email;

    private String password;

    private String genre;

    private LocalDate bornDate;

    private String phoneNumber;

    private String adminKey;

    private String imageCode;

    private Integer fkLevel;

    private Integer isBlocked;

    @OneToMany (orphanRemoval = true)
    @JsonManagedReference
    private Set<News> news = new HashSet<>();

    public User() {
    }

    public User(Integer idUser, String name, String username, String email, String genre, String phoneNumber, Integer fkLevel) {
        this.idUser = idUser;
        this.name = name;
        this.username = username;
        this.email = email;
        this.genre = genre;
        this.phoneNumber = phoneNumber;
        this.fkLevel = fkLevel;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDate bornDate) {
        this.bornDate = bornDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getImageCode() {
        return imageCode;
    }

    public void setImageCode(String imageCode) {
        this.imageCode = imageCode;
    }

    public Integer getFkLevel() {
        return fkLevel;
    }

    public void setFkLevel(Integer fkLevel) {
        this.fkLevel = fkLevel;
    }

    public String adminKey() {
        return adminKey;
    }

    public void setAdminKey(String adminKey) {
        this.adminKey = adminKey;
    }

    public String getAdminKey() {
        return adminKey;
    }

    public Integer getIsBlocked() {
        return isBlocked;
    }

    public void setIsBlocked(Integer isBlocked) {
        this.isBlocked = isBlocked;
    }

    public Set<News> getNews() {
        return news;
    }

    public void setNews(Set<News> news) {
        this.news = news;
    }
}
