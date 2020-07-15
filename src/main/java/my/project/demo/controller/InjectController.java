package my.project.demo.controller;

import my.project.demo.model.Role;
import my.project.demo.model.User;
import my.project.demo.service.RoleService;
import my.project.demo.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Set;

@Component
public class InjectController {

    private final RoleService roleService;

    private final UserService userService;

    public InjectController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void injectUsers() {
        Role userRole = new Role();
        userRole.setRoleName(Role.RoleName.USER);
        User user = new User();

        user.setLogin("user@ukr.net");
        user.setPassword("1111");
        user.setRoles(Set.of(userRole));
        roleService.save(userRole);
        userService.save(user);

        Role adminRole = new Role();
        adminRole.setRoleName(Role.RoleName.ADMIN);
        User admin = new User();
        admin.setLogin("admin@gmail.com");
        admin.setPassword("9999");
        admin.setRoles(Set.of(adminRole));
        roleService.save(adminRole);
        userService.save(admin);
    }
}
