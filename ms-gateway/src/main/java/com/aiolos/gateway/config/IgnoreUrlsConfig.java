package com.aiolos.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Aiolos
 * @date 2021/8/19 9:42 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "source.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
