package com.smart.life.saas.web.fleet.dto;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Setter
@Getter
public class FleetDTO {

    @NotNull
    private String number;

    @NotNull
    private Long modelId;

    @NonNull
    @Min(1)
    private Integer capacity;

    @NonNull
    @Min(0)
    private Integer maxLuggage;

    private boolean active;

    @NotEmpty
    private List<MultipartFile> imageFiles;

}
