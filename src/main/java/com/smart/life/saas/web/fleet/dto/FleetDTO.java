package com.smart.life.saas.web.fleet.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Setter
@Getter
public class FleetDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    @NotNull
    private String number;

    @NotNull
    private Long modelId;

}
