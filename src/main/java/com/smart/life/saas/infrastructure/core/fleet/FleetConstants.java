package com.smart.life.saas.infrastructure.core.fleet;

public final class FleetConstants {

    public static final String FLEET_IMAGE_PATH = "fleetImages";
    public static final String BASE_PATH = "/fleets";
    public static final String GET_BY_ID_PATH = "/{id}";

    private FleetConstants() {
        throw new IllegalStateException("Fleet Constants");
    }
}
