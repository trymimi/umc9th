package com.example.umc.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "UMC 9th API",
                description = "UMC 9th 프로젝트 API 문서",
                version = "v1"
        )
)
@Configuration
public class SwaggerConfig {
}

