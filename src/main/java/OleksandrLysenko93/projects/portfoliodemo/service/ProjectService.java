package OleksandrLysenko93.projects.portfoliodemo.service;

import OleksandrLysenko93.projects.portfoliodemo.converter.ProjectConverter;
import OleksandrLysenko93.projects.portfoliodemo.data.project.ProjectSummary;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.Project;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import OleksandrLysenko93.projects.portfoliodemo.domain.repository.ProjectRepository;
import OleksandrLysenko93.projects.portfoliodemo.domain.repository.UserRepository;
import OleksandrLysenko93.projects.portfoliodemo.web.command.CreateProjectCommand;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;
    private final ProjectConverter projectConverter;

    public void add(CreateProjectCommand createProjectCommand) {
        log.debug("Dane projektu do dodania: {}", createProjectCommand);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getAuthenticatedUser(username);
        log.debug("Pobrano usera do przypisania : {}", user);

        Project projectToBeSaved = projectConverter.from(createProjectCommand);
        projectToBeSaved.setUser(user);
        log.debug("Dane encji projektu do dodania: {}", projectToBeSaved);

        projectRepository.save(projectToBeSaved);
        log.debug("Zapisano projekt: {}", projectToBeSaved);
    }

    public List<ProjectSummary> findUserProjects() {
        log.debug("Pobieranie informacji o projektach użytkownika");

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return projectRepository.findAllByUserUsername(username).stream()
                .map(projectConverter::toProjectSummary)
                .collect(Collectors.toList());
    }
}
