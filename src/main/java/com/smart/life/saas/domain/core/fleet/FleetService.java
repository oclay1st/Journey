package com.smart.life.saas.domain.core.fleet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FleetService {

    private FleetRepository fleetRepository;

    public FleetModel saveModel(FleetModel fleet) {
        return fleetRepository.saveModel(fleet);
    }

    public Fleet save(Fleet fleet) {
        return fleetRepository.save(fleet);
    }

}
