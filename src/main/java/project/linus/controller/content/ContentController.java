package project.linus.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.content.Content;
import project.linus.model.content.ContentManager;
import project.linus.service.content.ContentService;
import project.linus.util.generic.ObjectList;

import java.util.List;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @GetMapping("/{idContent}")
    public ResponseEntity<Content> getContent(@PathVariable Integer idContent) {
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

    @GetMapping("/export/csv")
    public ResponseEntity<ObjectList<Content>> exportContent(
            @RequestParam String fileTitle,
            @RequestParam String contentTitle,
            @RequestParam Integer listSize
    ) {
        return ResponseEntity.ok(contentService.exportContentCsv(fileTitle, contentTitle, listSize));
    }

    @GetMapping("/export/txt")
    public ResponseEntity<ObjectList<Content>> exportContentTxt(
            @RequestParam String fileTitle,
            @RequestParam String contentTitle,
            @RequestParam Integer listSize
    ) {
        return ResponseEntity.ok(contentService.exportContentTxt(fileTitle, contentTitle, listSize));
    }

    @PostMapping("/import")
    public ResponseEntity<Content> importContent(@RequestParam String fileTitle){
        fileTitle += ".txt";
        return ResponseEntity.ok(contentService.importContentTxt(fileTitle));
    }

}
