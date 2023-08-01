package com.example.demospring.config;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfluxDBConfig {

    @Value("${influxdb.url}")
    private String influxDbUrl;

    @Value("${influxdb.username}")
    private String influxDbUsername;

    @Value("${influxdb.password}")
    private String influxDbPassword;

    @Bean
    public InfluxDB influxDB() {
        return InfluxDBFactory.connect(influxDbUrl, influxDbUsername, influxDbPassword);
    }
}
