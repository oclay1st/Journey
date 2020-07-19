package com.smart.life.saas.web.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetService;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Tag(name = "Fleet Models")
@RequestMapping("/fleetModels")
public class FleetModelController {

    private FleetService fleetService;

    public FleetModelController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    @Operation(summary = "Create a new fleet model")
    @ApiResponses(value = { @ApiResponse(description = "A fleet model resource") })
    public ResponseEntity<FleetModel> save(@Valid @ModelAttribute FleetModelDTO fleetModelDTO) {
        return new ResponseEntity<>(new FleetModel(), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "List fleet models")
    public ResponseEntity<Page<FleetModel>> listModels(@RequestParam PageRequest pageRequest) {
        return ResponseEntity.ok(fleetService.findAllModels(pageRequest));
    }
}
