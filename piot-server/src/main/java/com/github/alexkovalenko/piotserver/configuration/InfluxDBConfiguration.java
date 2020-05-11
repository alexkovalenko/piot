package com.github.alexkovalenko.piotserver.configuration;

import org.influxdb.dto.Point;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.influxdb.DefaultInfluxDBTemplate;
import org.springframework.data.influxdb.InfluxDBConnectionFactory;
import org.springframework.data.influxdb.InfluxDBProperties;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.data.influxdb.converter.PointConverter;

@Configuration
@EnableConfigurationProperties(InfluxDBProperties.class)
public class InfluxDBConfiguration {

    @Bean
    public InfluxDBConnectionFactory influxDBConnectionFactory(InfluxDBProperties properties) {
        return new InfluxDBConnectionFactory(properties);
    }

    @Bean
    public PointConverter pointConverter() {
        return new PointConverter();
    }

    @Bean
    public InfluxDBTemplate<Point> pointTemplate(InfluxDBConnectionFactory influxDBConnectionFactory,
                                                 PointConverter pointConverter) {
        return new InfluxDBTemplate<>(influxDBConnectionFactory, pointConverter);
    }

    @Bean
    public DefaultInfluxDBTemplate defaultInfluxDBTemplate(InfluxDBConnectionFactory influxDBConnectionFactory) {
        return new DefaultInfluxDBTemplate(influxDBConnectionFactory);
    }
}
