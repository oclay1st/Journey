package com.smart.life.kernel;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PageResource {

    @Min(0L)
    @Parameter(description = "Zero-based page index (0..N)", schema = @Schema(type = "integer", defaultValue = "0"))
    private Integer page;

    @Min(1L)
    @Parameter(description = "The size of the page to be returned", schema = @Schema(type = "integer", defaultValue = "20"))
    private Integer pageSize;

    @Min(0L)
    @Parameter(description = "Amount of page number", schema = @Schema(type = "integer", defaultValue = "0"))
    private Integer totalPages;

    @Min(0L)
    @Parameter(description = "Amount of elements", schema = @Schema(type = "integer", defaultValue = "0"))
    private Integer totalElements;

    private List<Object> items;
}
