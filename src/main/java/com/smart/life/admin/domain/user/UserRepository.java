package com.smart.life.admin.domain.user;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findUserByUsername(String username);

}
