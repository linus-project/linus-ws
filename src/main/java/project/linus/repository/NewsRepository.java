package project.linus.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.news.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {
    News findByNewsTitle(String newsTitle);

    News findByIdNews(Integer idNews);
}
