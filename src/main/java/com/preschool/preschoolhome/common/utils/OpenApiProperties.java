package com.preschool.preschoolhome.common.utils;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@ConfigurationProperties(prefix = "openapi")
public class OpenApiProperties {

    private final Hospital hospital = new Hospital();
    private final NightHospital nightHospital = new NightHospital();

    @Getter
    @Setter
    public static class Hospital {
        private String baseUrl;
        private String dataUrl;
        private String serviceKey;
    }

    @Getter
    @Setter
    public static class NightHospital {
        private String baseUrl;
        private String dataUrl;
        private String serviceKey;
    }
}
