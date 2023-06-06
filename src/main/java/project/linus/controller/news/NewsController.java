package project.linus.controller.news;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import project.linus.controller.login.LoginController;
import project.linus.model.news.News;
import project.linus.model.news.NewsManager;
import project.linus.service.news.NewsService;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/news")
public class NewsController {

    @Autowired
    NewsService newsService;

    Logger logger = Logger.getLogger(LoginController.class.getName());

    @GetMapping
    public ResponseEntity<List<News>> getNewsAll() {
        logger.info("Class: NewsController - Method: getNewsAll");
        return ResponseEntity.ok(newsService.getNewsAll());
    }

    @GetMapping("/idNews")
    public ResponseEntity<News> getNewsId(@RequestParam Integer idNews) {
        logger.info("Class: NewsController - Method: getNewsId");
        return ResponseEntity.of(newsService.getNewsId(idNews));
    }

    @GetMapping("/orderById")
    public ResponseEntity<List<News>> getOrderById(){
        return ResponseEntity.ok(newsService.OrderById());
    }

    @PostMapping
    public ResponseEntity<News> createNews(@RequestBody News newsManager){
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

    @GetMapping("/export")
    public ResponseEntity<String> exportUsers(@RequestParam Integer listSize, @RequestParam String fileTitle, @RequestParam Integer fkDistro) {
        return ResponseEntity.ok(newsService.exportNews(listSize,fileTitle,fkDistro));
    }

    @PostMapping("/import")
    public ResponseEntity<Void> importContent(@RequestParam("file") MultipartFile file) throws IOException {
        newsService.importNewsTxt(new String(file.getBytes()));
        return ResponseEntity.status(200).build();
    }

}
