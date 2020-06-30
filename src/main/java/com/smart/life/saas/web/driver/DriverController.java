package com.smart.life.saas.web.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.domain.core.driver.ListDriversUseCase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/drivers")
@RestController
public class DriverController {

    private final ListDriversUseCase listDriversUseCase;

    public DriverController(ListDriversUseCase listDriversUseCase) {
        this.listDriversUseCase = listDriversUseCase;
    }

    @GetMapping
    public Page<Driver> getAllDrivers(Pageable pageable) {
        return listDriversUseCase.execute(pageable);
    }
}
