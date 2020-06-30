package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryDAO extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
