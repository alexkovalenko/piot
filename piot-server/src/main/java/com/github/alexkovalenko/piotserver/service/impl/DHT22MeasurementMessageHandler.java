package com.github.alexkovalenko.piotserver.service.impl;

import com.github.alexkovalenko.piotserver.entity.DHT22Measurement;
import com.github.alexkovalenko.piotserver.entity.SensorType;
import com.github.alexkovalenko.piotserver.service.PayloadParser;
import com.github.alexkovalenko.piotserver.service.PiotMessageHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.influxdb.dto.Point;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

import static com.github.alexkovalenko.piotserver.entity.Constants.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DHT22MeasurementMessageHandler implements PiotMessageHandler {

    private static final String DHT22_MEASUREMENT_TOPIC_NAME =
            TOPIC_NAME_ROOT + BACKSLASH + SensorType.DHT22.name().toLowerCase() + BACKSLASH + MEASUREMENT;

    private final PayloadParser payloadParser;
    private final InfluxDBTemplate<Point> pointTemplate;

    @Override
    public boolean supportMessage(Message<?> message) {
        String topic = message.getHeaders().get(MESSAGE_HEADER_TOPIC, String.class);
        return DHT22_MEASUREMENT_TOPIC_NAME.equals(topic);
    }

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            payloadParser.parse(message.getPayload(), DHT22Measurement.class)
                    .ifPresent(this::persistSensorMeasurement);
        } catch (Exception e) {
            log.warn("Failed to persis sensor data, message {}", message, e);
        }
    }

    private void persistSensorMeasurement(DHT22Measurement sensorMeasurement) {
        pointTemplate.write(Point.measurement(sensorMeasurement.getType().name().toLowerCase())
                .time(System.currentTimeMillis(), TimeUnit.MILLISECONDS)
                .tag(TAG_DEVICE_ID, sensorMeasurement.getDeviceId())
                .tag(TAG_PLACEMENT, sensorMeasurement.getPlacement())
                .addField(FIELD_TEMPERATURE, sensorMeasurement.getTemperature())
                .addField(FIELD_HUMIDITY, sensorMeasurement.getHumidity())
                .build());
    }
}
