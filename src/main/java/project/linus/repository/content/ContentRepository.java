package project.linus.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.content.Content;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    Content findByIdContent(Integer idContent);
    Content findByContentTitle(String contentTitle);
    List<Content> findByContentTitleContains(String contentTitle);
    List<Content> findByFkLevel(Integer level);
    List<Content> findByFkDistro(Integer fkDistro);
    List<Content> findByFkDistroAndFkLevel(Integer fkDistro, Integer fkLevel);
}
