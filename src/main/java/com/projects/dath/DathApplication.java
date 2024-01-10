package com.projects.dath;

import com.projects.dath.config.s3.S3Properties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(value = {S3Properties.class})
public class DathApplication {
    public static void main(String[] args) {
        SpringApplication.run(DathApplication.class, args);
    }
    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Dadn")
                        .version("1.0.0")
                        .description("This is openAPI for an ecommerce website that sells smartphone"));
    }
}
