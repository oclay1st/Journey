package com.smart.life.saas.web.driver;

import com.smart.life.saas.domain.core.driver.DriverService;
import com.smart.life.saas.infrastructure.core.driver.DriverConstants;
import com.smart.life.saas.web.driver.dto.DriverDTO;
import com.smart.life.saas.web.driver.dto.DriverResource;
import com.smart.life.saas.web.driver.dto.DriverResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tag(name="Drivers")
@RequestMapping(DriverConstants.BASE_PATH)
@RestController
public class DriverController {

    private final DriverService driverService;

    private final DriverResourceAssembler driverResourceAssembler;

    public DriverController(DriverService driverService, DriverResourceAssembler driverResourceAssembler) {
        this.driverService = driverService;
        this.driverResourceAssembler = driverResourceAssembler;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a new driver")
    @ApiResponse(description = "The create driver resource")
    public ResponseEntity<DriverResource> create(@ModelAttribute @Valid DriverDTO driverDTO) {
        return new ResponseEntity<>(driverResourceAssembler.toModel(driverService.create(driverDTO)), HttpStatus.CREATED);
    }

}
