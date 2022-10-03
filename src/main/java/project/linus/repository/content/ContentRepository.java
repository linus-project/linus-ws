package project.linus.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.content.Content;

@Repository
public interface ContentRepository extends JpaRepository<Content, Integer> {
    Content findByContentTitle(String contentTitle);
}
