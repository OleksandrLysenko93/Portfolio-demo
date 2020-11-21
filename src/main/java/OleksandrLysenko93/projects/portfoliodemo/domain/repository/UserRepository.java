package OleksandrLysenko93.projects.portfoliodemo.domain.repository;

import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsername(String username);

    Optional<User> findByUsername(String username);

}
