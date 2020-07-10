package com.smart.life.saas.web.driver.dto;

import com.smart.life.saas.domain.core.common.Gender;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;

@Setter
@Getter
public class DriverDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotNull
    private String licenseId;
    private Integer bookingLimit;
    private LocalDate birthday;
    private Gender gender;
}
