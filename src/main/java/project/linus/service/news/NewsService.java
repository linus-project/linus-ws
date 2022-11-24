package project.linus.service.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.login.AdminLogin;
import project.linus.model.news.News;
import project.linus.model.news.NewsManager;
import project.linus.repository.NewsRepository;
import project.linus.service.login.LoginService;
import project.linus.util.exception.GenericException;

import java.util.List;
import java.util.Optional;

@Service
public class NewsService {

    @Autowired
    NewsRepository newsRepository;

    @Autowired
    LoginService loginService;

    public Optional<News> getNews(Integer idNews) {
        return newsRepository.findById(idNews);
    }

    public List<News> getNewsAll() {
        return newsRepository.findAll();
    }

    public boolean verifyIfNewsTitleExists(String newsTitle) {
        return newsRepository.findByNewsTitle(newsTitle) != null;
    }

    public News createNews(NewsManager newsManager) {
        News news = new News();

        AdminLogin admin = new AdminLogin(
                newsManager.getUsername(),
                newsManager.getPassword(),
                newsManager.getAdminkey());

        if (!verifyIfNewsTitleExists(newsManager.getNewsTitle())) {

            if (loginService.login(admin) != null) {
                news.setNewsTitle(newsManager.getNewsTitle());
                news.setNews(newsManager.getNews());
                newsRepository.save(news);
                return news;
            }
        }
        throw new GenericException();
    }

    public News editNews(NewsManager newsManager) {
        News news = newsRepository.findByIdNews(newsManager.getIdNews());

        AdminLogin admin = new AdminLogin(
                newsManager.getUsername(),
                newsManager.getPassword(),
                newsManager.getAdminkey());

        if (news != null && loginService.login(admin) != null) {
            if (newsManager.getNewsTitle() != null || !newsManager.getNewsTitle().equals("")) {
                news.setNewsTitle(newsManager.getNewsTitle());
            }if (newsManager.getNews() != null || !newsManager.getNews().equals("")) {
                news.setNews(newsManager.getNews());
            }
            newsRepository.save(news);
            return news;
        }
        throw new GenericException();
    }

    public News deleteNews(NewsManager newsManager) {
        News news = newsRepository.findByIdNews(newsManager.getIdNews());

        AdminLogin admin = new AdminLogin(
                newsManager.getUsername(),
                newsManager.getPassword(),
                newsManager.getAdminkey());

        if (news != null && loginService.login(admin) != null) {
            newsRepository.delete(news);
            return news;
        }
        throw new GenericException();
    }


}
