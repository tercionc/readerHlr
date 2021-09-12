package io.hlr.huawei.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
@ConfigurationProperties("hlr.file")
public class FileConfig {
    private String path;
    private String name;
    private Integer chunk;
}
