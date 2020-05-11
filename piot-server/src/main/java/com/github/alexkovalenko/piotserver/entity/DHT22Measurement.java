package com.github.alexkovalenko.piotserver.entity;

import lombok.Data;

@Data
public class DHT22Measurement implements SensorMeasurement {
    SensorType type;
    String deviceId;
    String placement;
    Double temperature;
    Double humidity;
}
