package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetModelDAO extends JpaRepository<FleetModel, Long> {

    boolean existsByNameAndIdNot(String name, Long id);

    boolean existsById(Long Id);
}
