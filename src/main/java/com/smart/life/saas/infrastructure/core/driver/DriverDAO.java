package com.smart.life.saas.infrastructure.core.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DriverDAO extends JpaRepository<Driver, Long> {
}
