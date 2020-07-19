package com.smart.life.saas.web.fleet.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class FleetModelDTO implements Serializable {
    private static final Long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private boolean active;

    @NotNull
    private MultipartFile thumbImageFile;

}
