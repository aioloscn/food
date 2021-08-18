package com.aiolos.diners.controller.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Aiolos
 * @date 2021/8/18 9:02 下午
 */
@Component
@ConfigurationProperties("oauth2.client")
@Getter
@Setter
public class OAuth2ClientConfiguration {

    private String clientId;
    private String secret;
    private String grant_type;
    private String scope;
}
