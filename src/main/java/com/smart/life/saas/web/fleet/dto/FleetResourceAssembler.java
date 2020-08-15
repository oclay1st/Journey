package com.smart.life.saas.web.fleet.dto;

import com.smart.life.kernel.SimpleNameResource;
import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.infrastructure.core.fleet.FleetConstants;
import com.smart.life.saas.web.fleet.FleetController;
import com.smart.life.saas.web.fleet.FleetModelController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class FleetResourceAssembler extends RepresentationModelAssemblerSupport<Fleet, FleetResource> {

    private final FileStorageService fileStorageService;

    public FleetResourceAssembler(FileStorageService fileStorageService) {
        super(FleetController.class, FleetResource.class);
        this.fileStorageService = fileStorageService;
    }

    @Override
    public FleetResource toModel(Fleet entity) {
        FleetResource fleetResource = createModelWithId(entity.getId(), entity);
        SimpleNameResource model = new SimpleNameResource();
        model.setId(entity.getModel().getId());
        model.setName(entity.getModel().getName());
        model.add(linkTo(methodOn(FleetModelController.class).getFleetModelById(model.getId())).withSelfRel());
        fleetResource.setId(entity.getId());
        fleetResource.setModel(model);
        fleetResource.setNumber(entity.getNumber());
        fleetResource.setActive(entity.isActive());
        fleetResource.setCapacity(entity.getCapacity());
        fleetResource.setMaxLuggage(entity.getMaxLuggage());
        List<String> imageUrls = entity.getImages().stream()
                .map(image -> fileStorageService.getURL(FleetConstants.FLEET_IMAGE_PATH + "/" + image))
                .collect(Collectors.toList());
        fleetResource.setImageUrls(imageUrls);
        return fleetResource;
    }
}
