package my.project.demo.service;

import my.project.demo.model.Role;

public interface RoleService {
    Role save(Role role);
    Role  findRoleByName(String roleName);
}
