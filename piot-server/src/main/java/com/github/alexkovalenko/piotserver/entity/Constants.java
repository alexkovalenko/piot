package com.github.alexkovalenko.piotserver.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String MESSAGE_HEADER_TOPIC = "mqtt_receivedTopic";
    public static final String TOPIC_NAME_ROOT = "piot";
    public static final String TAG_DEVICE_ID = "device_id";
    public static final String TAG_PLACEMENT = "placement";
    public static final String FIELD_TEMPERATURE = "temperature";
    public static final String FIELD_HUMIDITY = "humidity";
    public static final String BACKSLASH = "/";
    public static final String MEASUREMENT = "measurement";
}
