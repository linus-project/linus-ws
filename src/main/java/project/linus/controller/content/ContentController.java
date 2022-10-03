package project.linus.controller.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.content.Content;
import project.linus.service.content.ContentService;

@RestController
@RequestMapping("/content")
public class ContentController {

    @Autowired
    ContentService contentService;

    @GetMapping("/{idContent}")
    public ResponseEntity<Content> getContent(@PathVariable Integer idContent){
        return ResponseEntity.of(contentService.getContent(idContent));
    }

    @PostMapping("/create")
    public ResponseEntity<Content> createContent(@RequestBody Content content){
        return ResponseEntity.ok(contentService.createContent(content));
    }

}
