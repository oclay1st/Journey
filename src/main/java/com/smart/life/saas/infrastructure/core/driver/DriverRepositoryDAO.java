package com.smart.life.saas.infrastructure.core.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


public interface DriverRepositoryDAO extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {
}
