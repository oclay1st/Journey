package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.User;
import com.smart.life.saas.domain.core.user.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryJPA implements UserRepository {

    private final UserRepositoryDAO userRepositoryDAO;

    public UserRepositoryJPA(UserRepositoryDAO userRepositoryDAO) {
        this.userRepositoryDAO = userRepositoryDAO;
    }

    @Override
    public Optional<User> findUserByUsername(String username) {
        return userRepositoryDAO.findByUsername(username);
    }
}
