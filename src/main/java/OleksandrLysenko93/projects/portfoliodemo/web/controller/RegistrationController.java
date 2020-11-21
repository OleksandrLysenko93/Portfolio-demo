package OleksandrLysenko93.projects.portfoliodemo.web.controller;
import OleksandrLysenko93.projects.portfoliodemo.service.UserService;
import OleksandrLysenko93.projects.portfoliodemo.web.command.RegisterUserCommand;
import com.zaxxer.hikari.metrics.MetricsTrackerFactory;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.validation.Valid;

@Controller @Slf4j @RequiredArgsConstructor
@RequestMapping("/register")
public class RegistrationController {

    private final UserService userService;

    @GetMapping
    public String getRegister(Model model) {
        model.addAttribute(new RegisterUserCommand());
        return "register/form";
    }

    @PostMapping
    // Do rejestracji potrzebujemy: nazwa użytkownika, hasło użytkownika
    // Można tworzyć obiekty typu:
    // - RegisterUserDTO
    // - RegisterUserForm
    // - RegisterUserRequest
    // - RegisterUserCommand
    public String processRegister(@Valid RegisterUserCommand registerUserCommand, BindingResult bindingResult) {
        log.debug("Data for user creation: {}", registerUserCommand);
        if (bindingResult.hasErrors()) {
            log.debug("Wrong data: {}", bindingResult.getAllErrors());
            return "register/form";
        }

        Long id = userService.create(registerUserCommand);
        log.debug("Created user with id = {}", id);
        return "redirect:/login";
    }
}