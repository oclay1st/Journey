package com.smart.life.saas.domain.core.driver;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Driver {

    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long Id;
    private String licenseId;
    private Integer bookingLimit;
    private LocalDate birthday;
}
