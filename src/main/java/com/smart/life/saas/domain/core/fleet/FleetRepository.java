package com.smart.life.saas.domain.core.fleet;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FleetRepository {

    Fleet save(Fleet fleet);

    FleetModel saveModel(FleetModel fleet);

    Page<FleetModel> findAllModels(Pageable pageable);

}
