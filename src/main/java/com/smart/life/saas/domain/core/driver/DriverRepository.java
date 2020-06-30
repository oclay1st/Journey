package com.smart.life.saas.domain.core.driver;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DriverRepository {

    Optional<Driver> findById(Long Id);

    Page<Driver> findAll(Pageable pageable);

    Driver save(Driver driver);
}
