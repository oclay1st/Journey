package com.smart.life.saas.domain.core.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByEmail(String email);

    Optional<User> findByEmailAndRoleName(String email, UserRole userRole);

    Optional<User> findByEmailAndPassword(String email, String password);

    User save(User user);
}
