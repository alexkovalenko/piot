package com.github.alexkovalenko.piotserver.service.impl;

import com.github.alexkovalenko.piotserver.service.PiotMessageHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import static com.github.alexkovalenko.piotserver.entity.Constants.MESSAGE_HEADER_TOPIC;
import static com.github.alexkovalenko.piotserver.entity.Constants.TOPIC_NAME_ROOT;

@Slf4j
@Component
public class LogPiotMessageHandler implements PiotMessageHandler {

    @Override
    public boolean supportMessage(Message<?> message) {
        String topic = message.getHeaders().get(MESSAGE_HEADER_TOPIC, String.class);
        return topic != null && topic.startsWith(TOPIC_NAME_ROOT);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        log.info(String.valueOf(message));
    }
}
