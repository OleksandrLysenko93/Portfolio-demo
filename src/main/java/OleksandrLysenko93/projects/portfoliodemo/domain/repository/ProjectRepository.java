package OleksandrLysenko93.projects.portfoliodemo.domain.repository;

import OleksandrLysenko93.projects.portfoliodemo.domain.model.Project;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;
import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByUserUsername(String username);
}
