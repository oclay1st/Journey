package com.smart.life.saas.web.fleet.dto;

import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.infrastructure.core.fleet.FleetModelConstants;
import com.smart.life.saas.web.fleet.FleetModelController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;


@Component
public class FleetModelResourceAssembler extends RepresentationModelAssemblerSupport<FleetModel, FleetModelResource> {

    private final FileStorageService fileStorageService;

    public FleetModelResourceAssembler(FileStorageService fileStorageService) {
        super(FleetModelController.class, FleetModelResource.class);
        this.fileStorageService = fileStorageService;
    }

    @Override
    public FleetModelResource toModel(FleetModel entity) {
        FleetModelResource fleetModelResource = createModelWithId(entity.getId(), entity);
        fleetModelResource.setId(entity.getId());
        fleetModelResource.setName(entity.getName());
        fleetModelResource.setActive(entity.isActive());
        String thumbImageUrl = fileStorageService.getURL(FleetModelConstants.MODEL_THUMB_IMAGE_PATH + "/" + entity.getThumbImage());
        fleetModelResource.setThumbImageUrl(thumbImageUrl);
        return fleetModelResource;
    }
}
