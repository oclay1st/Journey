package com.smart.life.saas.web.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetService;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fleetModels")
public class FleetModelController {

    private FleetService fleetService;

    public FleetModelController(FleetService fleetService) {
        this.fleetService = fleetService;
    }

    @PostMapping
    public ResponseEntity<FleetModel> save(@Valid FleetModelDTO fleetModelDTO) {
        return new ResponseEntity<>(new FleetModel(), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<FleetModel>> listModels() {
        Pageable pageable = PageRequest.of(1, 100);
        return ResponseEntity.ok(fleetService.findAllModels(pageable));
    }
}
