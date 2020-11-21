package OleksandrLysenko93.projects.portfoliodemo.converter;

import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import OleksandrLysenko93.projects.portfoliodemo.web.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

@Component
public class UserConverter {

    public User from(RegisterUserCommand registerUserCommand) {
        return User.builder()
                .username(registerUserCommand.getUsername())
                .password(registerUserCommand.getPassword())
                .build();
    }
}
