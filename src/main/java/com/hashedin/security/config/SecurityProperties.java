package com.hashedin.security.config;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(prefix = "security")
@Data
@Configuration
public class SecurityProperties {
  private List<String> ignoreList;
}
