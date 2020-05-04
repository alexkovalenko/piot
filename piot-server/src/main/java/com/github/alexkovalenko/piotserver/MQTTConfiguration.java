package com.github.alexkovalenko.piotserver;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQTTConfiguration {

    @Value("${mqtt.serverURI}")
    private String serverURI;
    @Value("${mqtt.clientId}")
    private String clientId;

    @Bean
    public MqttClient mqttClient() throws MqttException {
        MqttClient mqttClient = new MqttClient(serverURI, clientId);
        mqttClient.connect();
        return mqttClient;
    }
}
