package com.ab.cqrs.query.controller;

import com.ab.cqrs.query.service.EventSourcingServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;

@RequiredArgsConstructor
@RestController
@RequestMapping("/queries/events")
public class EventSourcingController {

    private final EventSourcingServiceImpl eventSourcingService;

    @GetMapping("/account/{accountId}")
    public Stream<?> getAccountEvents(@PathVariable String accountId) {
        return eventSourcingService.getAccountEvents(accountId);
    }
}
