package com.smart.life.saas.web.driver;

import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.domain.core.driver.ListDriversUseCase;
import com.smart.life.saas.domain.core.driver.RegisterDriverUseCase;
import com.smart.life.saas.web.driver.dto.DriverDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/drivers")
@RestController
public class DriverController {

    private final ListDriversUseCase listDriversUseCase;
    private final RegisterDriverUseCase registerDriverUseCase;

    public DriverController(ListDriversUseCase listDriversUseCase, RegisterDriverUseCase registerDriverUseCase){
        this.listDriversUseCase = listDriversUseCase;
        this.registerDriverUseCase = registerDriverUseCase;
    }

    @GetMapping
    Page<Driver> getAllDrivers(Pageable pageable){
        return listDriversUseCase.execute(pageable);
    }

    @GetMapping("/{id}")
    Metting getDriver(@PathVariable Long id){
        Metting test =  new Metting(id,"Hola mundo");
        test.setName("This is a test");
        return test;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    Driver registerDriver(DriverDTO driverDTO){
        Driver driver =  new Driver();
        driver.setBirthday(driverDTO.getBirthday());
        driver.setBookingLimit(driverDTO.getBookingLimit());
        driver.setLicenseId(driverDTO.getLicenseId());
        return registerDriverUseCase.execute(driver);
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public class Metting {
        private Long id;
        private String name;
    }
}
