package com.aurionpro.repository.security;

import com.aurionpro.entity.enums.RoleName;
import com.aurionpro.entity.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName name);
}
