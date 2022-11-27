package project.linus.model.news;

import project.linus.util.manager.Manager;

public class NewsManager extends Manager {
    private String adminkey;
    private Integer idNews;
    private String newsTitle;
    private String news;

    public NewsManager(String username, String password) {
        super(username, password);
    }

    public String getAdminkey() {
        return adminkey;
    }

    public void setAdminkey(String adminkey) {
        this.adminkey = adminkey;
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

}
