package com.smart.life.saas.domain.core.driver;

import com.smart.life.saas.domain.core.common.Gender;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String licenseId;

    private Integer bookingLimit;

    private LocalDate birthday;

    private Gender gender;
}
