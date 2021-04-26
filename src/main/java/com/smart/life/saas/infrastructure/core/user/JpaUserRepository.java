package com.smart.life.admin.infrastructure.user;

import com.smart.life.admin.domain.user.User;
import com.smart.life.admin.domain.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final UserDAO userDAO;

    public JpaUserRepository(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userDAO.findByUsername(username);
    }
}
