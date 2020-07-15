package my.project.demo.security;

import my.project.demo.model.Role;
import my.project.demo.model.User;
import my.project.demo.service.RoleService;
import my.project.demo.service.UserService;
import my.project.demo.service.impl.UserServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public AuthenticationServiceImpl(UserService userService, PasswordEncoder passwordEncoder, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User login(String login, String password) {
        User userFromDB = userService.getByLogin(login);
        if (userFromDB != null && passwordEncoder.matches(password, userFromDB.getPassword())) {
            LOGGER.info("The user is authenticated");
            return userFromDB;
        }
        throw new RuntimeException("Wrong login or password");
    }

    @Override
    public User register(String login, String password) {
        User user = new User();
        user.setLogin(login);
        user.setPassword(passwordEncoder.encode(password));
        Role role = roleService.findRoleByName("USER");
        user.setRoles(Set.of(role));
        User registeredUser = userService.save(user);
        LOGGER.info("The user is registered!!!" + password);
        return registeredUser;
    }
}
