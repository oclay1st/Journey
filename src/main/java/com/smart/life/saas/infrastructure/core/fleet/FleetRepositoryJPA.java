package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class FleetRepositoryJPA implements FleetRepository {

    private final FleetRepositoryDAO fleetRepositoryDAO;

    private final FleetModelRepositoryDAO fleetModelRepositoryDAO;

    @Override
    public Fleet save(Fleet fleet) {
        return fleetRepositoryDAO.save(fleet);
    }

    @Override
    public FleetModel saveModel(FleetModel fleetModel) {
        return fleetModelRepositoryDAO.save(fleetModel);
    }
}
