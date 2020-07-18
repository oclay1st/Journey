package com.smart.life.saas.domain.core.fleet;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;

    public FleetModel saveModel(FleetModel fleet) {
        return fleetRepository.saveModel(fleet);
    }

    public Page<FleetModel> findAllModels(Pageable pageable) {
        return fleetRepository.findAllModels(pageable);
    }

    public Fleet save(Fleet fleet) {
        return fleetRepository.save(fleet);
    }

}
