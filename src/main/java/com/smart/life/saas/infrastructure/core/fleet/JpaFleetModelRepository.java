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
public class JpaFleetModelRepository implements FleetModelRepository {

    private final FleetModelDAO fleetModelDAO;

    public FleetModel save(FleetModel fleetModel) {
        return fleetModelDAO.save(fleetModel);
    }

    @Override
    public Page<FleetModel> findAll(Pageable pageable) {
        return fleetModelDAO.findAll(pageable);
    }

    @Override
    public Optional<FleetModel> findById(Long id) {
        return fleetModelDAO.findById(id);
    }

    @Override
    public boolean existsByNameAndIdNot(String name, Long id) {
        return fleetModelDAO.existsByNameAndIdNot(name, id);
    }

    @Override
    public boolean existById(Long id) {
        return fleetModelDAO.existsById(id);
    }
}
