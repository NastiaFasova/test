package my.project.demo.security;

import my.project.demo.model.User;
import my.project.demo.service.UserService;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userService.getByLogin(login);
        org.springframework.security.core.userdetails.User.UserBuilder userBuilder;
        if (user != null) {
            userBuilder = org.springframework.security.core.userdetails.User.withUsername(login);
            userBuilder.password(user.getPassword());
            String[] roles = user.getRoles().stream()
                    .map(r -> r.getRoleName().name())
                    .toArray(String[]::new);
            userBuilder.roles(roles);
            return userBuilder.build();
        }
        throw new UsernameNotFoundException("User not found.");
    }
}
