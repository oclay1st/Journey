package com.smart.life.saas.infrastructure.core.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DriverRepositoryDAO extends JpaRepository<Driver, Long>, JpaSpecificationExecutor<Driver> {

    @Override
    Optional<Driver> findById(Long Id);
}
