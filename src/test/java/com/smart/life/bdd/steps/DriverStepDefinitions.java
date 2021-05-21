package com.smart.life.bdd.steps;

import com.smart.life.bdd.config.SpringCucumberIntegration;
import com.smart.life.saas.domain.core.user.Role;
import com.smart.life.saas.domain.core.user.RoleRepository;
import com.smart.life.saas.domain.core.user.UserRole;
import com.smart.life.saas.infrastructure.core.driver.DriverConstants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.ResultActions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class DriverStepDefinitions extends SpringCucumberIntegration {

    private ResultActions context;

    @Autowired
    private RoleRepository roleRepository;

    @Before
    public void setUp() {
        super.setUp();
    }

    /** Jhon wants to create a new driver */

    @When("^Jhon creates a new driver$")
    @WithUserDetails("jhon@admin.com")
    public void jhonCreateANewDriver() throws Exception {
        roleRepository.save(new Role(null, UserRole.DRIVER));
        MockMultipartFile profileImage = new MockMultipartFile("imageFile", "profile.jpg", MediaType.IMAGE_JPEG_VALUE,
                "profile.jpg".getBytes());
        mockMvc.perform(multipart(DriverConstants.BASE_PATH).file(profileImage).param("name", "Bob")
                .param("lastName", "Miler").param("email", "bob@email.com").param("password", "password-text")
                .param("phones", "0145587985").param("phones", "0145587986").param("active", "true")
                .param("licenseId", "0011li").param("dailyBookingLimit", "10").param("birthday", "1980-01-01")
                .param("gender", "MALE").param("languages", "es"));

    }

    @Then("^the driver should be created$")
    public void theDriverShouldBeCreated() throws Exception {
        context.andExpect(status().isCreated())
            .andExpect(jsonPath("$.name", comparesEqualTo("Bob")))
            .andExpect(jsonPath("$.lastName", comparesEqualTo("Miler")))
            .andExpect(jsonPath("$.email", comparesEqualTo("bob@email.com")))
            .andExpect(jsonPath("$.password").doesNotExist())
            .andExpect(jsonPath("$.phones").isArray())
            .andExpect(jsonPath("$.phones", hasItems("0145587985", "0145587986")))
            .andExpect(jsonPath("$.active", comparesEqualTo(true)))
            .andExpect(jsonPath("$.licenseId", comparesEqualTo("0011li")))
            .andExpect(jsonPath("$.dailyBookingLimit", comparesEqualTo(10)))
            .andExpect(jsonPath("$.birthday", comparesEqualTo("1980-01-01")))
            .andExpect(jsonPath("$.gender", comparesEqualTo("MALE")))
            .andExpect(jsonPath("$.imageUrl").isNotEmpty())
            .andExpect(jsonPath("$.languages").isArray())
            .andExpect(jsonPath("$.languages", hasSize(1)))
            .andExpect(jsonPath("$.languages", hasItem("es")));
    }
}
