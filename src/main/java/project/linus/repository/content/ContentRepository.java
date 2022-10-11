package project.linus.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.util.content.Content;

import java.util.List;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    Content findByContentTitle(String contentTitle);
    List<Content> findByContentTitleContains(String contentTitle);
}
