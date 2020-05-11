package com.github.alexkovalenko.piotserver.service;

import org.springframework.messaging.Message;

public interface PiotMessageHandler extends org.springframework.messaging.MessageHandler {
    boolean supportMessage(Message<?> message);
}
