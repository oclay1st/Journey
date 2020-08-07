package com.smart.life.saas.infrastructure.core.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.domain.core.driver.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaDriverRepository implements DriverRepository {

    private final DriverDAO driverDAO;

    public JpaDriverRepository(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }

    public Optional<Driver> findById(Long id) {
        return driverDAO.findById(id);
    }

    public Page<Driver> findAll(Pageable pageable) {
        return driverDAO.findAll(pageable);
    }

    public Driver save(Driver driver) {
        return driverDAO.save(driver);
    }
}
