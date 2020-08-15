package com.smart.life.saas.fleet;


import com.smart.life.saas.domain.core.fleet.Fleet;
import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
import com.smart.life.saas.domain.core.fleet.FleetRepository;
import com.smart.life.saas.infrastructure.core.fleet.FleetConstants;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FleetControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FleetModelRepository fleetModelRepository;

    @Autowired
    private FleetRepository fleetRepository;


    @Test
    public void createFleet() throws Exception {
        MockMultipartFile fleetImage = new MockMultipartFile("imageFiles", "taxi.jpg", MediaType.IMAGE_JPEG_VALUE, "imge".getBytes());
        fleetModelRepository.save(new FleetModel(1L, "model", true, "fleetImage"));
        mockMvc.perform(
                multipart(FleetConstants.BASE_PATH).file(fleetImage)
                        .param("number", "001")
                        .param("modelId", "1")
                        .param("capacity", "5")
                        .param("maxLuggage", "4")
                        .param("active", "true")
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.number", comparesEqualTo("001")))
                .andExpect(jsonPath("$.capacity", comparesEqualTo(5)))
                .andExpect(jsonPath("$.model").isMap())
                .andExpect(jsonPath("$.model.id", comparesEqualTo(1)))
                .andExpect(jsonPath("$.model.name", comparesEqualTo("model")))
                .andExpect(jsonPath("$.maxLuggage", comparesEqualTo(4)))
                .andExpect(jsonPath("$.active", comparesEqualTo(true)))
                .andExpect(jsonPath("$.imageUrls").isNotEmpty());
    }

    @Test
    public void findAllFleets_empty() throws Exception {
        mockMvc.perform(
                get(FleetConstants.BASE_PATH)
                        .queryParam("page", "1")
                        .queryParam("size", "10")
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.page").exists())
                .andExpect(jsonPath("$.page.totalElements", is(0)));
    }

    @Test
    public void findFleetById() throws Exception {
        FleetModel model = fleetModelRepository.save(new FleetModel(null, "ford", true, "modelImage.jpg"));
        Fleet fleet = fleetRepository.save(new Fleet(null, "ford123", model, 4, 5, true, Arrays.asList("fleet.png", "fleet2.png")));
        mockMvc.perform(
                get(FleetConstants.BASE_PATH + "/" + fleet.getId())
        ).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", comparesEqualTo(1)))
                .andExpect(jsonPath("$.number", comparesEqualTo("ford123")))
                .andExpect(jsonPath("$.capacity", comparesEqualTo(4)))
                .andExpect(jsonPath("$.maxLuggage", comparesEqualTo(5)))
                .andExpect(jsonPath("$.imageUrls").isArray())
                .andExpect(jsonPath("$.imageUrls", hasSize(2)));
    }
}
