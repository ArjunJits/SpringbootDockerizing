package com.app.mapper.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

@ConfigurationProperties(prefix = "app.ftp")
@Validated
public record AppConfig(@Positive int version, @NotEmpty String username,String password,@Positive int port) {
 
}
