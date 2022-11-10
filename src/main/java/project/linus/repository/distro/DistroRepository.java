package project.linus.repository.distro;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.linus.model.content.Content;
import project.linus.model.distro.Distro;

import java.util.List;

@Repository
public interface DistroRepository extends JpaRepository<Distro, Long> {
    Distro findByDistroName(String distroName);
    List<Distro> findByDistroNameContains(String distroName);
    List<Distro> findByFkLevel(Long level);

}
