package project.linus.model.news;

import com.fasterxml.jackson.annotation.JsonBackReference;
import project.linus.model.user.User;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "tb_news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idNews;

    private String newsTitle;

    private String news;
    
    @ManyToOne
    @JoinColumn(name = "fk_user")
    @JsonBackReference
    private User fkUser;

    private final LocalDateTime insertDate = LocalDateTime.now();

    public News() {

    }

    public News(Integer idNews, String newsTitle, String news, User fkUser) {
        this.idNews = idNews;
        this.newsTitle = newsTitle;
        this.news = news;
        this.fkUser = fkUser;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNews() {
        return news;
    }

    public void setNews(String news) {
        this.news = news;
    }

    public User getFkUser() {
        return fkUser;
    }

    public void setFkUser(User fkUser) {
        this.fkUser = fkUser;
    }
    
    public LocalDateTime getInsertDate() {
        return insertDate;
    }
}
