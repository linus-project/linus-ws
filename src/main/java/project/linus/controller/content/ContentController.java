package project.linus.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.controller.login.LoginController;
import project.linus.model.content.Content;
import project.linus.model.content.ContentManager;
import project.linus.model.content.UserFavoriteContent;
import project.linus.model.content.UserHistoryContent;
import project.linus.service.content.ContentService;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    Logger logger = Logger.getLogger(LoginController.class.getName());


    @GetMapping("/{idContent}")
    public ResponseEntity<Content> getContent(@PathVariable Integer idContent) {
        logger.info("Class: ContentController - Method: getContent");
        return ResponseEntity.of(contentService.getContent(idContent));
    }

    @GetMapping
    public ResponseEntity<List<Content>> getContentByTitle(@RequestParam(required = false) String contentTitle) {
        return ResponseEntity.ok(contentService.getContentByTitle(contentTitle));
    }

    @GetMapping("/level/{level}")
    public ResponseEntity<List<Content>> getContentByLevel(@PathVariable Integer level) {
        return ResponseEntity.ok(contentService.getContentByLevel(level));
    }

    @GetMapping("/distro/{distro}/level/{level}")
    public ResponseEntity<List<Content>> getContentByFkDistroAndFkLevel(
            @PathVariable Integer distro,
            @PathVariable Integer level
    ) {
        return ResponseEntity.ok(contentService.getContentByFkDistroAndFkLevel(distro, level));
    }

    @PostMapping
    public ResponseEntity<Content> createContent(@RequestBody ContentManager contentManager) {
        return ResponseEntity.ok(contentService.createContent(contentManager));
    }

    @PutMapping
    public ResponseEntity<Content> editContent(@RequestBody ContentManager contentManager) {
        return ResponseEntity.ok(contentService.editContent(contentManager));
    }

    @DeleteMapping
    public ResponseEntity<Content> deleteContent(@RequestBody ContentManager contentManager) {
        return ResponseEntity.ok(contentService.deleteContent(contentManager));
    }

    @GetMapping("/favorite")
    public ResponseEntity<Boolean> getFavoriteContent(@RequestParam Integer idUser, @RequestParam Integer idContent) {
        logger.info("Class: ContentController - Method: getFavoriteContent");
        return ResponseEntity.ok(contentService.getFavoriteContent(idUser, idContent));
    }

    @GetMapping("/favorite/level")
    public ResponseEntity<List<Content>> getFavoriteContentByLevel(@RequestParam Integer idUser, @RequestParam Integer level) {
        logger.info("Class: ContentController - Method: getFavoriteContentByLevel");
        return ResponseEntity.ok(contentService.getFavoriteContentByLevel(idUser, level));
    }

    @PostMapping("/favorite")
    public ResponseEntity<Content> favoriteContent(@RequestBody UserFavoriteContent userFavoriteContent) {
        logger.info("Class: ContentController - Method: favoriteContent");
        return ResponseEntity.ok(contentService.favoriteContent(userFavoriteContent));
    }

    @PostMapping("/history")
    public ResponseEntity<Content> historyContent(@RequestBody UserHistoryContent userHistoryContent) {
        logger.info("Class: ContentController - Method: historyContent \nBody: " + userHistoryContent);
        return ResponseEntity.ok(contentService.historyContent(userHistoryContent));
    }

    @GetMapping("/history")
    public ResponseEntity<List<Content>> getHistoryContentByLevel(@RequestParam Integer idUser) {
        logger.info("Class: ContentController - Method: historyContentByIdUser - \nidUser: " + idUser);
        return ResponseEntity.ok(contentService.getHistoryContentByLevel(idUser));
    }

    @GetMapping("/export/csv")
    public ResponseEntity<String> exportContent(
            @RequestParam String fileTitle,
            @RequestParam String contentTitle,
            @RequestParam Integer listSize
    ) {
        return ResponseEntity.ok(contentService.exportContentCsv(fileTitle, contentTitle, listSize));
    }

    @GetMapping("/export/txt")
    public ResponseEntity<String> exportContentTxt(
            @RequestParam String fileTitle,
            @RequestParam String contentTitle,
            @RequestParam Integer listSize
    ) {
        return ResponseEntity.ok(contentService.exportContentTxt(fileTitle, contentTitle, listSize));
    }

    @PostMapping("/import")
    public ResponseEntity<Content> importContent(@RequestParam String fileTitle) {
        fileTitle += ".txt";
        return ResponseEntity.ok(contentService.importContentTxt(fileTitle));
    }
}
