package com.smart.life.bdd.steps;

import com.smart.life.bdd.config.SpringCucumberIntegration;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
import com.smart.life.saas.infrastructure.core.fleet.FleetModelConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FleetModelStepDefinitions extends SpringCucumberIntegration {

    private ResultActions context;

    @Autowired
    private FleetModelRepository fleetModelRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    /** Jhon wants to create a fleet model */

    @When("^Jhon creates a new fleet model$")
    @WithUserDetails("jhon@admin.com")
    public void jhonCreateANewFleetModel() throws Exception {
        MockMultipartFile image = new MockMultipartFile("thumbImageFile", "test image.jpg", MediaType.IMAGE_JPEG_VALUE,
                "<<jpg data>>".getBytes());
        mockMvc.perform(
                multipart(FleetModelConstants.BASE_PATH).file(image).param("name", "Audi").param("active", "true"));

    }

    @Then("^the fleet model should be created$")
    public void theFleetModelShouldBeCreated() throws Exception {
        context.andExpect(status().isCreated()).andExpect(jsonPath("$.name", comparesEqualTo("Audi")))
                .andExpect(jsonPath("$.thumbImageUrl", notNullValue()))
                .andExpect(jsonPath("$.active", comparesEqualTo(true)))
                .andExpect(jsonPath("$._links.self", notNullValue()));
    }

    /** Jhon wants to see a fleet model */

    @And("^a fleet model with name Mercedes exists$")
    public void aFleetModelWithANameExists() {
        fleetModelRepository.save(new FleetModel(null, "Mercedes", false, "test_image1.png"));
    }

    @Then("^Jhon wants to see the fleet model Mercedes$")
    @WithUserDetails("jhon@admin.com")
    public void jhonWantsToSeeTheFleetModel() throws Exception {
        mockMvc.perform(get(FleetModelConstants.BASE_PATH + "/" + 1)).andExpect(status().isOk());

    }

    @Then("^Jhon should get the fleet model Mercedes$")
    public void jhonShouldGetTheFleetModel() throws Exception {
        context.andExpect(jsonPath("$.name", comparesEqualTo("Mercedes")))
                .andExpect(jsonPath("$.thumbImageUrl", endsWith("test_image1.png")))
                .andExpect(jsonPath("$.active", comparesEqualTo(false)))
                .andExpect(jsonPath("$._links.self", notNullValue()));
    }

    /** Jhon wants to see a list offleet models */

    @And("^a list of fleet models exists$")
    public void aListOfFleetModelsExists() throws Exception {
        fleetModelRepository.save(new FleetModel(null, "Mercedes", true, "test_image1.png"));
        fleetModelRepository.save(new FleetModel(null, "Toyota", true, "test_image2.png"));
        fleetModelRepository.save(new FleetModel(null, "", true, "test_image3.png"));
    }

    @When("^Jhon wants to see the fleet models$")
    @WithUserDetails("jhon@admin.com")
    public void jhonWantsToSeeTheFleetModels() throws Exception {
        mockMvc.perform(get(FleetModelConstants.BASE_PATH).queryParam("page", "0").queryParam("size", "20"));
    }

    @When("^the fleet models should be listed$")
    public void theFleetModelsShouldBeListed() throws Exception {
        context.andExpect(status().isOk()).andExpect(jsonPath("$._embedded.fleetModels", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()));
    }

}
