package com.smart.life.bdd.steps;

import com.smart.life.bdd.config.SpringCucumberIntegration;
import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
import com.smart.life.saas.domain.core.fleet.FleetRepository;
import com.smart.life.saas.infrastructure.core.fleet.FleetConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

public class FleetStepDefinitions extends SpringCucumberIntegration {

    private ResultActions context;

    @Autowired
    private FleetModelRepository fleetModelRepository;

    @Autowired
    private FleetRepository fleetRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    /** Jhon wants to create a fleet */

    @When("^Jhon creates a new fleet$")
    @WithUserDetails("jhon@admin.com")
    public void jhonCreateANewFleet() throws Exception {
        MockMultipartFile fleetImage = new MockMultipartFile("imageFiles", "taxi.jpg", MediaType.IMAGE_JPEG_VALUE,
                "imge".getBytes());
        fleetModelRepository.save(new FleetModel(1L, "model", true, "fleetImage"));
        mockMvc.perform(multipart(FleetConstants.BASE_PATH).file(fleetImage).param("number", "001")
                .param("modelId", "1").param("capacity", "5").param("maxLuggage", "4").param("active", "true"));
    }

    @Then("^the fleet should be created$")
    public void theFleetShouldBeCreated() throws Exception {
        context.andExpect(status().isCreated()).andExpect(jsonPath("$.number", comparesEqualTo("001")))
                .andExpect(jsonPath("$.capacity", comparesEqualTo(5))).andExpect(jsonPath("$.model").isMap())
                .andExpect(jsonPath("$.model.id", comparesEqualTo(1)))
                .andExpect(jsonPath("$.model.name", comparesEqualTo("model")))
                .andExpect(jsonPath("$.maxLuggage", comparesEqualTo(4)))
                .andExpect(jsonPath("$.active", comparesEqualTo(true))).andExpect(jsonPath("$.imageUrls").isNotEmpty());
    }

    /** Jhon wants to see a fleet */

    @And("^a fleet with exists$")
    public void aFleetExists() {
        FleetModel model = fleetModelRepository.save(new FleetModel(null, "ford", true, "modelImage.jpg"));
        fleetRepository
                .save(new Fleet(null, "ford123", model, 4, 5, true, Arrays.asList("fleet.png", "fleet2.png")));
    }

    @Then("^Jhon wants to see the fleet")
    @WithUserDetails("jhon@admin.com")
    public void jhonWantsToSeeTheFleet() throws Exception {
        mockMvc.perform(get(FleetConstants.BASE_PATH + "/" + 1));

    }

    @Then("^Jhon should get the fleet$")
    public void jhonShouldGetTheFleet() throws Exception {
        context.andExpect(status().isOk()).andExpect(jsonPath("$.id", comparesEqualTo(1)))
                .andExpect(jsonPath("$.number", comparesEqualTo("ford123")))
                .andExpect(jsonPath("$.capacity", comparesEqualTo(4)))
                .andExpect(jsonPath("$.maxLuggage", comparesEqualTo(5))).andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls", hasSize(2)));
    }

}
