package com.smart.life.saas.web.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.domain.core.driver.DriverService;
import com.smart.life.saas.web.driver.dto.DriverDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/drivers")
@RestController
public class DriverController {

    private final DriverService driverService;

    public DriverController(DriverService driverService) {
        this.driverService = driverService;
    }

    @PostMapping
    public ResponseEntity<Driver> save(@Valid DriverDTO driverDTO) {
        return new ResponseEntity<>(driverService.save(new Driver()), HttpStatus.CREATED);
    }

}
