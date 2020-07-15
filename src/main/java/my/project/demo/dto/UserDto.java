package my.project.demo.dto;

import lombok.Data;
import my.project.demo.model.Role;

import java.util.Set;

@Data
public class UserDto {
    private String login;
    private String password;
    private Set<Role> roles;
}
