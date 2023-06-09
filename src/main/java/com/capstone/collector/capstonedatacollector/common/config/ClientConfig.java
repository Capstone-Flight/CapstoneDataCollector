package com.capstone.collector.capstonedatacollector.common.config;

import com.capstone.collector.capstonedatacollector.clients.AviationStackClientCaller;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientConfig {
    @Bean
    @Qualifier("restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean(name="aviationStackClientCaller")
    public AviationStackClientCaller aviationStackClientCaller(RestTemplate restTemplate, @Value("${aviation.base.url}") String baseUrl, @Value("${aviation.key}") String key) {
        return new AviationStackClientCaller(restTemplate, baseUrl, key);
    }
}
