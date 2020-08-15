package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetDAO extends JpaRepository<Fleet, Long> {

    boolean existsByNumberAndIdNot(String number, Long id);
}
