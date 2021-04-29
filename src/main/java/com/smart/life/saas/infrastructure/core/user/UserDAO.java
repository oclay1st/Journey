package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmailAndRoleName(String email, UserRole userRole);

    Optional<User> findByEmailAndPassword(String email, String password);
}
