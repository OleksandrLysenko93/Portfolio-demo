package OleksandrLysenko93.projects.portfoliodemo.web.controller;

import OleksandrLysenko93.projects.portfoliodemo.service.ProjectService;
import OleksandrLysenko93.projects.portfoliodemo.web.command.CreateProjectCommand;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("project")
@Slf4j
@RequiredArgsConstructor
public class CreateProjectController {

    private final ProjectService projectService;

    @GetMapping
    public String getProject(Model model) {
        model.addAttribute(new CreateProjectCommand());
        return "project/project";
    }

}
