package com.smart.life.saas.fleet;


import com.smart.life.saas.domain.core.fleet.FleetModel;
import com.smart.life.saas.domain.core.fleet.FleetModelRepository;
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

import static org.hamcrest.Matchers.comparesEqualTo;
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

}
