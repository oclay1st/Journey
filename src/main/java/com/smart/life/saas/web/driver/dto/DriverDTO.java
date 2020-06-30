package com.smart.life.saas.web.driver.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
public class DriverDTO implements Serializable {

    private final Long SerialVersionUID = 1L;

    private String licenseId;
    private Integer bookingLimit;
    private LocalDate birthday;
}
