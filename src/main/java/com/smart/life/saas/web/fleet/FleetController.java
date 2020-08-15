package com.smart.life.saas.web.fleet;

import com.smart.life.kernel.JourneyException;
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

@Tag(name = "Fleets")
@RestController
@RequestMapping(FleetConstants.BASE_PATH)
@AllArgsConstructor
public class FleetController {

    private final FleetService fleetService;
    private final FleetResourceAssembler fleetResourceAssembler;
    private final PagedResourcesAssembler<Fleet> pagedResourcesAssembler;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Create a fleet entity")
    @ApiResponse(description = "A fleet resource")
    public ResponseEntity<FleetResource> save(@ModelAttribute @Valid FleetDTO fleetDTO) {
        Fleet fleet = fleetService.create(fleetDTO);
        return new ResponseEntity<>(fleetResourceAssembler.toModel(fleet), HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve a collection of fleet by pagination")
    @ApiResponse(description = "A collection of fleets")
    public ResponseEntity<CollectionModel<FleetResource>> findAll(@RequestParam(defaultValue = "0") Integer page,
                                                                  @RequestParam(defaultValue = "20") Integer size) {
        Page<Fleet> fleetPage = fleetService.findAll(PageRequest.of(page, size));
        return ResponseEntity.ok(pagedResourcesAssembler.toModel(fleetPage, fleetResourceAssembler));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a fleet by id")
    @ApiResponse(description = "A fleet entity resource")
    public ResponseEntity<FleetResource> findById(@PathVariable Long id) {
        Optional<Fleet> fleet = fleetService.findById(id);
        if (!fleet.isPresent()) {
            throw JourneyException.notFound("Not found a fleet with Id : " + id);
        }
        return ResponseEntity.ok(fleetResourceAssembler.toModel(fleet.get()));
    }
}
