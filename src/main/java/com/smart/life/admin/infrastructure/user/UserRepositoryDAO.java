package com.smart.life.admin.infrastructure.user;

import com.smart.life.admin.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepositoryDAO extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
