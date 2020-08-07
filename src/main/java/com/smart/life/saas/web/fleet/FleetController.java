package com.smart.life.saas.web.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetService;
import com.smart.life.saas.infrastructure.core.fleet.FleetConstants;
import com.smart.life.saas.web.fleet.dto.FleetDTO;
import com.smart.life.saas.web.fleet.dto.FleetResource;
import com.smart.life.saas.web.fleet.dto.FleetResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Tag(name = "Fleets")
@RestController
@RequestMapping(FleetConstants.BASE_PATH)
@AllArgsConstructor
public class FleetController {

    private final FleetService fleetService;
    private final FleetResourceAssembler fleetResourceAssembler;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a fleet entity")
    @ApiResponse(description = "A fleet resource")
    public ResponseEntity<FleetResource> save(@ModelAttribute @Valid FleetDTO fleetDTO) {
        Fleet fleet = fleetService.create(fleetDTO);
        return new ResponseEntity<>(fleetResourceAssembler.toModel(fleet), HttpStatus.CREATED);
    }
}
