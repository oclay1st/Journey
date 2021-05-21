package com.smart.life.bdd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
public abstract class SpringCucumberIntegration {

    @Autowired
    private WebApplicationContext webApplicationContext;

    protected MockMvc mockMvc;


    public void setUp() {

        // MockMVC
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply(springSecurity())// applying Spring Security
                .build();
    }

}