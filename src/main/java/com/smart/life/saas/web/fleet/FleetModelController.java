package com.smart.life.saas.web.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.web.fleet.dto.FleetModelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fleetModels")
public class FleetModelController {

    @PostMapping
    public ResponseEntity<FleetModel> save(@Valid FleetModelDTO fleetModelDTO) {
        return new ResponseEntity<>(new FleetModel(), HttpStatus.CREATED);
    }
}
