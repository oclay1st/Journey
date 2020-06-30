package com.smart.life.saas.domain.core.driver;

import org.springframework.stereotype.Service;

@Service
public class RegisterDriverUseCase {

    private final DriverRepository driverRepository;

    public RegisterDriverUseCase(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    public Driver execute(Driver driver) {
        return driverRepository.save(driver);
    }
}
