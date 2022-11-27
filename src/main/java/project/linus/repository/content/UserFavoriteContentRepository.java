package project.linus.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.content.UserFavoriteContent;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavoriteContentRepository extends JpaRepository<UserFavoriteContent, Integer> {

    Optional<UserFavoriteContent> findByFkUserAndFkContent(Integer fkUser, Integer fkContent);

    List<UserFavoriteContent> findByFkUserAndContentLevel(Integer fkUser, Integer contentLevel);

}
