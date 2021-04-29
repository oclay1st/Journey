package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.Role;
import com.smart.life.saas.domain.core.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleDAO extends JpaRepository<Role,Long> {

    Optional<Role> findByName(UserRole userRole);
}
