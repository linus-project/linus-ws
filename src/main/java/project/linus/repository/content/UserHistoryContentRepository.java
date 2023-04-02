package project.linus.repository.content;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.content.UserFavoriteContent;
import project.linus.model.content.UserHistoryContent;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserHistoryContentRepository extends JpaRepository<UserHistoryContent, Integer> {

    Optional<UserHistoryContent> findByFkUserAndFkContent(Integer fkUser, Integer fkContent);

    List<UserHistoryContent> findByFkUser(Integer fkUser);

    List<UserHistoryContent> findByFkUserAndContentLevel(Integer fkUser, Integer contentLevel);

}
