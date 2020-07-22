package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetModelRepositoryDAO extends JpaRepository<FleetModel, Long> {

    <S extends FleetModel> boolean existsByNameAndIdNot(String name, Long id);
}
