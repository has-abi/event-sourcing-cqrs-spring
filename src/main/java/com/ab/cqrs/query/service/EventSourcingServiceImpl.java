package com.ab.cqrs.query.service;

import com.ab.cqrs.query.service.EventSourcingService;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@RequiredArgsConstructor
@Service
public class EventSourcingServiceImpl implements EventSourcingService {

    private final EventStore eventStore;

    public Stream<?> getAccountEvents(String accountId) {
        return eventStore.readEvents(accountId).asStream();
    }
}
