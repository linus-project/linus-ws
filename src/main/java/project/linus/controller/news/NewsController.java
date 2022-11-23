package project.linus.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.news.News;
import project.linus.model.news.NewsManager;
import project.linus.service.news.NewsService;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    @GetMapping
    public ResponseEntity<List<News>> getNewsAll() {
        return ResponseEntity.ok(newsService.getNewsAll());
    }

    @GetMapping("/idNews")
    public ResponseEntity<News> getNews(@RequestParam Integer idNews) {
        return ResponseEntity.of(newsService.getNews(idNews));
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody NewsManager newsManager){
        return ResponseEntity.ok(newsService.createNews(newsManager));
    }

    @PutMapping
    public ResponseEntity<News> editNews(@RequestBody NewsManager newsManager) {
        return ResponseEntity.ok(newsService.editNews(newsManager));
    }

    @DeleteMapping
    public ResponseEntity<News> deleteNews(@RequestBody NewsManager newsManager) {
        return ResponseEntity.ok(newsService.deleteNews(newsManager));
    }

}
