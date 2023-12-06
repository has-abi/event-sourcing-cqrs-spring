package com.ab.cqrs.command.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class DebitAccountCommand extends BaseCommand<String> {
    @Getter
    private final BigDecimal amount;

    public DebitAccountCommand(String id, BigDecimal amount) {
        super(id);
        this.amount = amount;
    }
}
