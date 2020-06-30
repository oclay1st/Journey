package com.smart.life.saas.domain.core.driver;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ListDriversUseCase {

    private final DriverRepository driverRepository;

    public Page<Driver> execute(Pageable pageable) {
        return driverRepository.findAll(pageable);
    }
}
	