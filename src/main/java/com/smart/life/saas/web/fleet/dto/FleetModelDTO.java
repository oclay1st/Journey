package com.smart.life.saas.web.fleet.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class FleetModelDTO {

    @NotNull
    private String name;

    @NotNull
    private boolean active;

    @NotNull
    private MultipartFile thumbImageFile;

}
