package com.smart.life.saas.domain.core.user;

import com.smart.life.kernel.CrudBaseRepository;

import java.util.Optional;

public interface RoleRepository extends CrudBaseRepository<Role,Long> {

    Optional<Role> findByName(UserRole userRole);
}
