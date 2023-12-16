package ru.akhramova.createthreatmodel.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.info.Info;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${parser.restltm}")
    private String url;

    @Autowired
    private BuildProperties buildProperties;

    @Bean
    public OpenAPI myOpenAPI() {
        Server server = new Server();
        server.setUrl(url);
        server.setDescription("Server URL");

        Info info = new Info()
                .title("Data download API")
                .version(buildProperties.getVersion())
                .description("API для автоматизированных выгрузок");

        return new OpenAPI().info(info).servers(List.of(server));
    }

}
