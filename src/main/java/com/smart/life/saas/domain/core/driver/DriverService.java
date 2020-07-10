package com.smart.life.saas.domain.core.driver;

import org.springframework.stereotype.Service;

@Service
public class DriverService {

    private final DriverRepository driverRepository;

    public DriverService(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver save(Driver driver) {
        return driverRepository.save(driver);
    }

}
