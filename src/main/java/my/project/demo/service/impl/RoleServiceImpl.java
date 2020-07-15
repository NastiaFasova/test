package my.project.demo.service.impl;

import my.project.demo.model.Role;
import my.project.demo.repository.RoleRepository;
import my.project.demo.service.RoleService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {
    private static final Logger LOGGER = Logger.getLogger(RoleServiceImpl.class);
    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role save(Role role) {
        LOGGER.info("The role was saved into db");
        return roleRepository.save(role);
    }

    @Override
    public Role findRoleByName(String roleName) {
        LOGGER.info("The role was found by name");
        return roleRepository.findRoleByRoleName(Role.RoleName.valueOf(roleName));
    }
}
