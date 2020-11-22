package OleksandrLysenko93.projects.portfoliodemo.web.controller;

import OleksandrLysenko93.projects.portfoliodemo.service.ProjectService;
import OleksandrLysenko93.projects.portfoliodemo.web.command.CreateProjectCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.management.RuntimeErrorException;
import javax.validation.Valid;

@Controller
@RequestMapping("projects/add")
@Slf4j
@RequiredArgsConstructor
public class CreateProjectController {

    private final ProjectService projectService;

    @GetMapping("/add")
    public String getProject(Model model) {
        model.addAttribute(new CreateProjectCommand());
        return "project/add";
    }

    @PostMapping("add")
    public String processAddProject(@Valid CreateProjectCommand createProjectCommand, BindingResult bindings) {
        log.debug("Projekt przekazany do zapisu: {}", createProjectCommand);

        if (bindings.hasErrors()) {
            log.debug("Błędne dane: {}", bindings.getAllErrors());
            return "project/add";
        }

        try {
            projectService.add(createProjectCommand);
            log.debug("Udane dodanie projektu: {}");
            return "redirect:/profile";
        } catch (RuntimeErrorException re) {
            log.warn(re.getLocalizedMessage());
            log.debug("Błąd przy dodawaniu projektu");
            bindings.rejectValue(null, null, "Wystąpił błąd");
            return "project/add";
        }
    }

}
