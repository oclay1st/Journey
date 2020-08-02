package com.smart.life.config;

import com.smart.life.kernel.PageResource;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;

import static org.springdoc.core.SpringDocUtils.getConfig;


@OpenAPIDefinition(
        info = @Info(
                title = "Journey API",
                description = "API for a journey to a better life",
                contact = @Contact(
                        name = "oclay1st",
                        url = "https://github.com/oclay1st"
                ),
                license = @License(
                        name = "MIT Licence",
                        url = "https://github.com/thombergs/code-examples/blob/master/LICENSE")),
        servers = @Server(url = "http://localhost:8080")
)
@Configuration
public class OpenApiConfig {

    static {
        getConfig().replaceWithClass(Page.class, PageResource.class);
    }

}
