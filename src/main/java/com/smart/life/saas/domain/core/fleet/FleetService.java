package com.smart.life.saas.domain.core.fleet;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class FleetService {

    private final FleetRepository fleetRepository;

}
