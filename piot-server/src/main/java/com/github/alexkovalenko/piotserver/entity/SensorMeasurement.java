package com.github.alexkovalenko.piotserver.entity;

public interface SensorMeasurement {
    SensorType getType();
    String getDeviceId();
    String getPlacement();
}
