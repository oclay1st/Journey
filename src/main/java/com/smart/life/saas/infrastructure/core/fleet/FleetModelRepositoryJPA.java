package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@AllArgsConstructor
public class FleetModelRepositoryJPA implements FleetModelRepository {

    private final FleetModelRepositoryDAO fleetModelRepositoryDAO;

    public FleetModel save(FleetModel fleetModel) {
        return fleetModelRepositoryDAO.save(fleetModel);
    }

    @Override
    public Page<FleetModel> findAll(Pageable pageable) {
        return fleetModelRepositoryDAO.findAll(pageable);
    }

    @Override
    public Optional<FleetModel> findById(Long id) {
        return fleetModelRepositoryDAO.findById(id);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return fleetModelRepositoryDAO.existsByNameAndIdNot(name, id);
    }
}
