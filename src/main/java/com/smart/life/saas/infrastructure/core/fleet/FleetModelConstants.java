package com.smart.life.saas.infrastructure.core.fleet;

public class FleetModelConstants {

    public static final String MODEL_THUMB_IMAGE_PATH = "modelImages";
    public static final String BASE_PATH = "/fleets/models";
    public static final String GET_BY_ID_PATH = "/{id}";

    private FleetModelConstants() {
        throw new IllegalStateException("Fleet Model Constants");
    }
}
