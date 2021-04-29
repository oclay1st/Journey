package com.smart.life.saas.infrastructure.core.driver;

public final class DriverConstants {

    public static final String DRIVER_IMAGE_PATH = "diverImages";
    public static final String BASE_PATH = "/drivers";
    public static final String GET_BY_ID_PATH = "/{id}";

    private DriverConstants(){
        throw new IllegalStateException("Driver Constants");
    }

}
