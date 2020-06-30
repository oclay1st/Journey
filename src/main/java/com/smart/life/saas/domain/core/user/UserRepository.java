package com.smart.life.saas.domain.core.user;

import java.util.Optional;

public interface UserRepository {

    public Optional<User> findUserByUsername(String username);

}
