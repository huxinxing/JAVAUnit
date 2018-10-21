package com.ml.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Data
@Component
@PropertySource(value = "classpath:resource.properties")
@ConfigurationProperties(prefix = "sys")
public class ResourceConfig {
    private String name;
}
