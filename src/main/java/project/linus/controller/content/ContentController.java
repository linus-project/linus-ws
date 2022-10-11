package project.linus.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.util.content.Content;
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

    @PostMapping("/create")
    public ResponseEntity<Content> createContent(@RequestBody Content content) {
        return ResponseEntity.ok(contentService.createContent(content));
    }

    @GetMapping("/export")
    public ResponseEntity<ObjectList<Content>> exportContent(
            @RequestParam String fileTitle,
            @RequestParam String contentTitle,
            @RequestParam Integer listSize
    ) {
        return ResponseEntity.ok(contentService.exportContent(fileTitle, contentTitle, listSize));
    }

}
