package com.ab.cqrs.command.events;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class BaseEvent<T> {
    private T id;
}
