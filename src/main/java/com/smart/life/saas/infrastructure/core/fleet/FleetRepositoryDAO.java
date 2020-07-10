package com.smart.life.saas.infrastructure.core.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FleetRepositoryDAO extends JpaRepository<Fleet, Long> {

}
