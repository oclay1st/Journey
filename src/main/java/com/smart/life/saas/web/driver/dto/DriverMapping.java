package com.smart.life.saas.web.driver.dto;

import com.smart.life.saas.domain.core.driver.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DriverMapping {

    DriverMapping INSTANCE = Mappers.getMapper(DriverMapping.class);

    @Mapping(target = "user.name", source = "name")
    @Mapping(target = "user.lastName", source = "lastName")
    @Mapping(target = "user.password", source = "password")
    @Mapping(target = "user.email", source = "email")
    @Mapping(target = "user.active", source = "active")
    Driver driverDTOtoDriver(DriverDTO driverDTO);

    @Mapping(target = "imageUrl", ignore = true)
    @Mapping(target = "name", source = "user.name")
    @Mapping(target = "lastName", source = "user.lastName")
    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "active", source = "user.active")
    DriverResource driverToDriverResource(Driver driver);

}
