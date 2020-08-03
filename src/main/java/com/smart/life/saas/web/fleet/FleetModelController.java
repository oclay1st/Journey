package com.smart.life.saas.web.fleet;

import com.smart.life.kernel.JourneyException;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelService;
import com.smart.life.saas.infrastructure.core.fleet.FleetModelConstants;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import com.smart.life.saas.web.fleet.dto.FleetModelResource;
import com.smart.life.saas.web.fleet.dto.FleetModelResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Tag(name = "Fleet Models")
@RestController
@RequestMapping(FleetModelConstants.BASE_PATH)
@AllArgsConstructor
public class FleetModelController {

    private final FleetModelService fleetModelService;

    private final FleetModelResourceAssembler fleetModelResourceAssembler;

    private final PagedResourcesAssembler<FleetModel> pagedResourcesAssembler;


    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    @Operation(summary = "Create a new fleet model")
    @ApiResponses(value = {@ApiResponse(description = "A fleet model resource")})
    public ResponseEntity<FleetModelResource> save(@ModelAttribute @Valid FleetModelDTO fleetModelDTO) {
        FleetModel model = fleetModelService.create(fleetModelDTO);
        return new ResponseEntity<>(fleetModelResourceAssembler.toModel(model), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "A paginate fleet model list")
    public ResponseEntity<CollectionModel<FleetModelResource>> listModels(@RequestParam Integer page, @RequestParam Integer size) {
        Page<FleetModel> fleetModels = fleetModelService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(fleetModels, fleetModelResourceAssembler));
    }

    @GetMapping(FleetModelConstants.GET_BY_ID_PATH)
    @Operation(summary = "Get a fleet model by id")
    public ResponseEntity<FleetModelResource> getFleetModelById(@PathVariable Long id) {
        Optional<FleetModel> fleetModel = fleetModelService.findById(id);
        if (!fleetModel.isPresent()) {
            throw JourneyException.notFound("Not found fleet model with Id : " + id);
        }
        return ResponseEntity.ok(fleetModelResourceAssembler.toModel(fleetModel.get()));
    }
}
