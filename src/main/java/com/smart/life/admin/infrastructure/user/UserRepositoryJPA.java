package com.smart.life.admin.infrastructure.user;

import com.smart.life.admin.domain.user.User;
import com.smart.life.admin.domain.user.UserRepository;
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
