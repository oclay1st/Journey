package com.smart.life.saas.infrastructure.core.user;

import com.smart.life.saas.domain.core.user.Role;
import com.smart.life.saas.domain.core.user.RoleRepository;
import com.smart.life.saas.domain.core.user.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class JpaRoleRepository implements RoleRepository {

    private final RoleDAO roleDAO;

    @Override
    public Role save(Role model) {
        return roleDAO.save(model);
    }

    @Override
    public Page<Role> findAll(Pageable pageable) {
        return roleDAO.findAll(pageable);
    }

    @Override
    public Optional<Role> findById(Long id) {
        return roleDAO.findById(id);
    }

    @Override
    public Optional<Role> findByName(UserRole userRole) {
        return roleDAO.findByName(userRole);
    }

}
