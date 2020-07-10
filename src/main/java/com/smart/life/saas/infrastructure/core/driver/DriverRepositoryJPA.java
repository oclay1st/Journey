package com.smart.life.saas.infrastructure.core.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.domain.core.driver.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class DriverRepositoryJPA implements DriverRepository {

    private final DriverRepositoryDAO driverRepositoryDAO;

    public DriverRepositoryJPA(DriverRepositoryDAO driverRepositoryDAO) {
        this.driverRepositoryDAO = driverRepositoryDAO;
    }

    public Optional<Driver> findById(Long id) {
        return driverRepositoryDAO.findById(id);
    }

    public Page<Driver> findAll(Pageable pageable) {
        return driverRepositoryDAO.findAll(pageable);
    }

    public Driver save(Driver driver) {
        return driverRepositoryDAO.save(driver);
    }
}
