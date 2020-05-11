package com.github.alexkovalenko.piotserver.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.alexkovalenko.piotserver.service.PayloadParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class PayloadParserImpl implements PayloadParser {

    private final ObjectMapper objectMapper;

    @Override
    public <T> Optional<T> parse(Object payload, Class<T> type) {
        return Optional.ofNullable(payload)
                .map(String::valueOf)
                .map(p -> deserialize(p, type));
    }

    private <T> T deserialize(String payload, Class<T> type) {
        try {
            return objectMapper.readValue(payload, type);
        } catch (JsonProcessingException e) {
            log.warn("Failed to deserialize sensor data from payload {}", payload, e);
            return null;
        }
    }
}
