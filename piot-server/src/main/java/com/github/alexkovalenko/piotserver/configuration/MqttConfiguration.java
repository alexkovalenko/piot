package com.github.alexkovalenko.piotserver.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alexkovalenko.piotserver.service.impl.MqttMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;

@Slf4j
@Configuration
public class MqttConfiguration {

    @Value("${mqtt.serverURL}")
    private String serverURL;
    @Value("${mqtt.clientId}")
    private String clientId;
    @Value("${mqtt.topics}")
    private String[] topics;

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter() {
        log.info("MQTT server url: {}", serverURL);
        return new MqttPahoMessageDrivenChannelAdapter(serverURL, clientId, topics);
    }

    @Bean
    public IntegrationFlow mqttInbound(MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter,
                                       MqttMessageHandler mqttMessageHandler) {
        return IntegrationFlows.from(mqttPahoMessageDrivenChannelAdapter)
                .handle(mqttMessageHandler)
                .get();
    }

    @Bean
    public IntegrationFlow mqttOutboundFlow() {
        return f -> f.handle(new MqttPahoMessageHandler(serverURL, clientId));
    }
}
