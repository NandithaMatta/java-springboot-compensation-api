package com.portfolio.payroll.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI payrollOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Payroll Management System API")
                        .description("Compensation platform REST API — manages employees, jobs, salary classes and market survey data")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Portfolio Project")
                                .email("your.email@example.com")));
    }
}