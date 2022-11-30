package project.linus.service.commentary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.linus.model.commentary.Commentary;
import project.linus.model.commentary.CommentaryResponse;
import project.linus.model.user.User;
import project.linus.repository.commentary.CommentaryRepository;
import project.linus.repository.user.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentaryService {

    @Autowired
    CommentaryRepository commentaryRepository;

    @Autowired
    UserRepository userRepository;

    public List<CommentaryResponse> getCommentary(Long idContent) {
        List<Commentary> commentaryList = commentaryRepository.findByFkContent(idContent);
        List<CommentaryResponse> commentaryResponse = new ArrayList<>();
        for (Commentary commentary : commentaryList) {
            User user = userRepository.findById(commentary.getFkUser()).get();
            commentaryResponse.add(new CommentaryResponse(
                    commentary.getIdCommentary(),
                    commentary.getCommentaryContent(),
                    commentary.getFkContent(),
                    commentary.getFkUser(),
                    user.getUsername())
            );
        }
        return commentaryResponse;
    }

    public Commentary createCommentary(Commentary commentary) {
        commentaryRepository.save(commentary);
        return commentary;
    }
}
