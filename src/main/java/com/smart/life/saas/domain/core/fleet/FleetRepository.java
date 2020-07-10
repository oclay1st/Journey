package com.smart.life.saas.domain.core.fleet;

public interface FleetRepository {

    Fleet save(Fleet fleet);

    FleetModel saveModel(FleetModel fleet);

}
