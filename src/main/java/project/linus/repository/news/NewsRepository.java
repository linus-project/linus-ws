package project.linus.repository.news;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.linus.model.news.News;
import project.linus.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    Optional<News> findByNewsTitle(String newsTitle);
    News findByIdNews(Integer idNews);

    boolean existsByFkDistro(Integer fkDistro);

    List<News> findByFkDistro(Integer fkDistro);

//    @Query("select n from News n")
//    List<News> getNews();
}
