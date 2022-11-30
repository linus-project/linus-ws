package project.linus.controller.commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.linus.model.commentary.Commentary;
import project.linus.model.commentary.CommentaryResponse;
import project.linus.service.commentary.CommentaryService;

import java.util.List;

@RestController
@RequestMapping("/commentary")
public class CommentaryController {

    @Autowired
    CommentaryService commentaryService;

    @GetMapping
    public ResponseEntity<List<CommentaryResponse>> getCommentary(@RequestParam Long idContent) {
        return ResponseEntity.ok(commentaryService.getCommentary(idContent));
    }

    @PostMapping
    public ResponseEntity<Commentary> createCommentary(@RequestBody Commentary commentary) {
        return ResponseEntity.ok(commentaryService.createCommentary(commentary));
    }


}
