package com.smart.life.saas.web.driver.dto;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.smart.life.saas.domain.common.Gender;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@JsonRootName(value = "driver")
@Relation(collectionRelation = "drivers")
public class DriverResource extends RepresentationModel<DriverResource> {

    private String name;

    private String lastName;

    private String email;

    private List<String> phones;

    private boolean active;

    private String licenseId;

    private Integer dailyBookingLimit;

    private LocalDate birthday;

    private Gender gender;

    private List<String> languages;

    private String imageUrl;

}
