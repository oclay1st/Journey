package com.smart.life.saas.web.driver.dto;

import com.smart.life.saas.domain.common.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Setter
@Getter
public class DriverDTO {

    @NotNull
    private String licenseId;
    private Integer bookingLimit;
    private LocalDate birthday;
    private Gender gender;
}
