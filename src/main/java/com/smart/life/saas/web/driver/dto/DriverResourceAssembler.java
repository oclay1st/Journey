package com.smart.life.saas.web.driver.dto;

import com.smart.life.saas.domain.common.FileStorageService;
import com.smart.life.saas.domain.core.driver.Driver;
import com.smart.life.saas.infrastructure.core.driver.DriverConstants;
import com.smart.life.saas.web.driver.DriverController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

@Component
public class DriverResourceAssembler extends RepresentationModelAssemblerSupport<Driver, DriverResource> {

    private final FileStorageService fileStorageService;

    public DriverResourceAssembler(FileStorageService fileStorageService) {
        super(DriverController.class, DriverResource.class);
        this.fileStorageService = fileStorageService;
    }

    @Override
    public DriverResource toModel(Driver entity) {
        DriverResource driverResource = DriverMapping.INSTANCE.driverToDriverResource(entity);
        driverResource.add(WebMvcLinkBuilder.linkTo(this.getControllerClass()).slash(entity.getId()).withSelfRel());
        String imageUrl = fileStorageService.getURL(DriverConstants.DRIVER_IMAGE_PATH + "/" + entity.getImage());
        driverResource.setImageUrl(imageUrl);
        return driverResource;
    }
}
