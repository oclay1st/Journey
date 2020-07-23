package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class FleetRepositoryJPA implements FleetRepository {

    private final FleetRepositoryDAO fleetRepositoryDAO;

    @Override
    public Fleet save(Fleet fleet) {
        return fleetRepositoryDAO.save(fleet);
    }

    @Override
    public Page<Fleet> findAll(Pageable pageable) {
        return fleetRepositoryDAO.findAll(pageable);
    }

    @Override
    public Optional<Fleet> findById(Long id) {
        return fleetRepositoryDAO.findById(id);
    }
}
