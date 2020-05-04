package com.github.alexkovalenko.piotserver;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
@RequiredArgsConstructor
public class MQTTEchoService {
    private final MqttClient mqttClient;

    @PostConstruct
    private void postConstruct() throws MqttException {
        mqttClient.subscribe("echo", (topic, message) -> {
            log.info("topic {} message {}", topic, new String(message.getPayload()));
        });
    }
}
