package OleksandrLysenko93.projects.portfoliodemo.converter;

import OleksandrLysenko93.projects.portfoliodemo.data.user.UserSummary;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.UserDetails;
import OleksandrLysenko93.projects.portfoliodemo.web.command.EditUserCommand;
import OleksandrLysenko93.projects.portfoliodemo.web.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class UserConverter {

    public static UserSummary toUserSummary(User user) {
        UserDetails details = user.getDetails();
        return UserSummary.builder()
                .username(user.getUsername())
                .firstName(details.getFirstName())
                .lastName(details.getLastName())
                .birthDate(details.getBirthDate())
                .build();
    }

    public User from(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUsername())
                .password(registerUserCommand.getPassword())
                .build();
    }

    public User from(EditUserCommand editUserCommand, User user) {
        UserDetails details = user.getDetails();
        details.setFirstName(editUserCommand.getFirstName());
        details.setLastName(editUserCommand.getLastName());
        details.setBirthDate(editUserCommand.getBirthDate());
        return user;
    }
}
