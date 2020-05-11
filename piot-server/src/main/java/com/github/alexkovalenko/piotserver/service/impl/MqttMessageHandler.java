package com.github.alexkovalenko.piotserver.service.impl;

import com.github.alexkovalenko.piotserver.service.PiotMessageHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MqttMessageHandler implements MessageHandler {

    private final List<PiotMessageHandler> messageHandlers;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        messageHandlers.stream()
                .filter(h -> h.supportMessage(message))
                .forEach(h -> h.handleMessage(message));
    }
}
