package com.smart.life.saas.fleet;

import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
import com.smart.life.saas.infrastructure.core.fleet.FleetModelConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FleetModelControllerTests {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private FleetModelRepository fleetModelRepository;

    @Test
    public void createFleetModel() throws Exception {
        MockMultipartFile image = new MockMultipartFile("thumbImageFile", "test image.jpg", MediaType.IMAGE_JPEG_VALUE,
                "<<jpg data>>".getBytes());
        mvc.perform(multipart(FleetModelConstants.BASE_PATH).file(image).param("name", "Audi").param("active", "true"))
                .andExpect(status().isCreated()).andExpect(jsonPath("$.name", comparesEqualTo("Audi")))
                .andExpect(jsonPath("$.thumbImageUrl", notNullValue()))
                .andExpect(jsonPath("$.active", comparesEqualTo(true)))
                .andExpect(jsonPath("$._links.self", notNullValue()));

    }

    @Test
    public void getFleetModelById() throws Exception {
        FleetModel model = fleetModelRepository.save(new FleetModel(null, "Mercedes", false, "test_image1.png"));
        mvc.perform(get(FleetModelConstants.BASE_PATH + "/" + model.getId())).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", comparesEqualTo("Mercedes")))
                .andExpect(jsonPath("$.thumbImageUrl", endsWith("test_image1.png")))
                .andExpect(jsonPath("$.active", comparesEqualTo(false)))
                .andExpect(jsonPath("$._links.self", notNullValue()));
    }

    @Test
    public void notFoundFleetModelById() throws Exception {
        mvc.perform(get(FleetModelConstants.BASE_PATH + "/1000")).andExpect(status().isNotFound());
    }

    @Test
    public void listFleetModels() throws Exception {
        fleetModelRepository.save(new FleetModel(null, "Mercedes", true, "test_image1.png"));
        fleetModelRepository.save(new FleetModel(null, "Toyota", true, "test_image2.png"));
        fleetModelRepository.save(new FleetModel(null, "", true, "test_image3.png"));
        mvc.perform(get(FleetModelConstants.BASE_PATH).queryParam("page", "0").queryParam("size", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.fleetModels", notNullValue()))
                .andExpect(jsonPath("$.page", notNullValue()));
    }

}
