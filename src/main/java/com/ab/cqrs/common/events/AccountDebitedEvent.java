package com.ab.cqrs.common.events;

import lombok.Getter;

import java.math.BigDecimal;

public class AccountDebitedEvent extends BaseEvent<String>{
    @Getter
    private final BigDecimal amount;

    public AccountDebitedEvent(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
