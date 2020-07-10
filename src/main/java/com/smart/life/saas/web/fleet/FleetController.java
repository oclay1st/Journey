package com.smart.life.saas.web.fleet;

import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.web.fleet.dto.FleetDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/fleets")
public class FleetController {

    @PostMapping
    public ResponseEntity<Fleet> save(@Valid FleetDTO fleetDTO) {
        return new ResponseEntity<>(new Fleet(), HttpStatus.CREATED);
    }
}
