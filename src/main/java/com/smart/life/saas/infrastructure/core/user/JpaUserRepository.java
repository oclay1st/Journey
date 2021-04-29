package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserRepository;
import com.smart.life.saas.domain.core.user.UserRole;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final UserDAO userDAO;

    public JpaUserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

    @Override
    public Optional<User> findByEmailAndRoleName(String email, UserRole userRole) {
        return userDAO.findByEmailAndRoleName(email, userRole);
    }

    @Override
    public User save(User user) {
        return userDAO.save(user);
    }

    @Override
    public Optional<User> findByEmailAndPassword(String email, String password) {
        return userDAO.findByEmailAndPassword(email, password);
    }
}
