package my.project.demo.repository;

import my.project.demo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r where r.roleName = :roleName")
    Role findRoleByRoleName(@Param("roleName")Role.RoleName roleName);
}
