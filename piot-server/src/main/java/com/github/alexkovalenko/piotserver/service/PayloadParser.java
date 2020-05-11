package com.github.alexkovalenko.piotserver.service;

import java.util.Optional;

public interface PayloadParser {

    <T> Optional<T> parse(Object payload, Class<T> type);
}
