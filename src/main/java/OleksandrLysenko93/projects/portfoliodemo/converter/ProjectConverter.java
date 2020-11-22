package OleksandrLysenko93.projects.portfoliodemo.converter;

import OleksandrLysenko93.projects.portfoliodemo.data.project.ProjectSummary;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.Project;
import OleksandrLysenko93.projects.portfoliodemo.web.command.CreateProjectCommand;
import org.springframework.stereotype.Component;

@Component
public class ProjectConverter {
    public static Project from(CreateProjectCommand createProjectCommand) {
        return Project.builder()
                .name(createProjectCommand.getName())
                .url(createProjectCommand.getUrl())
                .description(createProjectCommand.getDescription())
                .build();
    }

    public ProjectSummary toProjectSummary(Project project) {
        return ProjectSummary.builder()
                .name(project.getName())
                .url(project.getUrl())
                .description(project.getDescription())
                .username(project.getUser().getUsername())
                .build();
    }

}
