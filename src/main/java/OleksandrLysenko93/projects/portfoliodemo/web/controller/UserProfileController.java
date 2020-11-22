package OleksandrLysenko93.projects.portfoliodemo.web.controller;

import OleksandrLysenko93.projects.portfoliodemo.data.user.UserSummary;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.UserDetails;
import OleksandrLysenko93.projects.portfoliodemo.service.UserService;
import OleksandrLysenko93.projects.portfoliodemo.web.command.EditUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/profile")
@Slf4j @RequiredArgsConstructor
public class UserProfileController {

    private final UserService userService;

    @GetMapping
    public String getProfilePage(Model model) {
        UserSummary summary = userService.getCurrentUserSummary();
        EditUserCommand editUserCommand = createEditUserCommand(summary);
        model.addAttribute(summary);
        model.addAttribute(editUserCommand);
        return "user/profile";
    }

    private EditUserCommand createEditUserCommand(UserSummary summary) {
        return EditUserCommand.builder()
                .firstName(summary.getFirstName())
                .lastName(summary.getLastName())
                .birthDate(summary.getBirthDate())
                .build();
    }

    @PostMapping("/edit")
    public String editUserProfile(@Valid EditUserCommand editUserCommand, BindingResult bindings) {
        log.debug("Dane profilu usera do zapisu :{}" );
        return "redirect:/profile";
    }

}
