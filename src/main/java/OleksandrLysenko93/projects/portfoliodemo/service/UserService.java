package OleksandrLysenko93.projects.portfoliodemo.service;

import OleksandrLysenko93.projects.portfoliodemo.converter.UserConverter;
import OleksandrLysenko93.projects.portfoliodemo.data.user.UserSummary;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.User;
import OleksandrLysenko93.projects.portfoliodemo.domain.model.UserDetails;
import OleksandrLysenko93.projects.portfoliodemo.domain.repository.UserRepository;
import OleksandrLysenko93.projects.portfoliodemo.exception.UserAlreadyExistsException;
import OleksandrLysenko93.projects.portfoliodemo.web.command.EditUserCommand;
import OleksandrLysenko93.projects.portfoliodemo.web.command.RegisterUserCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Service
@Slf4j @RequiredArgsConstructor
public class UserService {

    private final UserConverter userConverter;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Long create(RegisterUserCommand registerUserCommand) {
        log.debug("Dane użytkownika do zapisania: {}", registerUserCommand);

        User userToCreate = userConverter.from(registerUserCommand);
        log.debug("Uzyskany obiekt użytkownika do zapisu: {}", userToCreate);
        if (userRepository.existsByUsername(userToCreate.getUsername())) {
            log.debug("Próba rejestracji na istniejącego użytkownika");
            throw new UserAlreadyExistsException(String.format("Użytkownik %s już istnieje", userToCreate.getUsername()));
        }

        userToCreate.setPassword(passwordEncoder.encode(userToCreate.getPassword()));
        setDefaultCredentials(userToCreate);
        userRepository.save(userToCreate);
        log.debug("Zapisany użytkownik: {}", userToCreate);

        return userToCreate.getId();
    }

    private void setDefaultCredentials(User userToCreate) {
        userToCreate.setActive(Boolean.TRUE);
        userToCreate.setRoles(Set.of("ROLE_USER"));
        userToCreate.setDetails(UserDetails.builder()
                .user(userToCreate)
                .build());
    }

    @Transactional
    public UserSummary getCurrentUserSummary() {
        log.debug("Pobieranie danych zalogowanego usera");
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.getAuthenticatedUser(username);
        UserSummary summary = UserConverter.toUserSummary(user);
        log.debug("Budowanie podsumowania danych użytkownika: {}", summary);

        return summary;
    }

    @Transactional
    public boolean edit(EditUserCommand editUserCommand) {
        log.debug("Dane usera do edycji", editUserCommand);

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepository.getAuthenticatedUser(username);
        log.debug("Edycja usera: {}", user);

        user = userConverter.from(editUserCommand, user);
        log.debug("Zmodyfikowany user: {}", user.getDetails());

        return true;
    }
}