package project.linus.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import project.linus.model.user.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByEmail(String email);
    Optional<User> findByIdUser(Integer idUser);

    boolean existsByUsername(String username);

    @Query("select u from User u")
    List<User> getUser();
}
