package com.ab.cqrs.command.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class CreditAccountCommand extends BaseCommand<String> {
    @Getter
    private final BigDecimal amount;

    public CreditAccountCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
