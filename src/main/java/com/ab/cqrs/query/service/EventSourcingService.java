package com.ab.cqrs.query.service;

import java.util.stream.Stream;

public interface EventSourcingService {
    Stream<?> getAccountEvents(String accountId);
}
