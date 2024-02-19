package com.testmall.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@ConfigurationProperties(prefix = "custom")
@RefreshScope
public class CustomProperty {
    public static String osType;
    public static String secretKey;


    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        CustomProperty.osType = osType;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        CustomProperty.secretKey = secretKey;
    }

    public CustomProperty() {
    }
    public CustomProperty(String osType) {
        this.osType = osType;
    }
}
