package com.ab.cqrs.command.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class CreateAccountCommand extends BaseCommand<String> {
    @Getter
    private final BigDecimal initialBalance;

    public CreateAccountCommand(String id, BigDecimal initialBalance) {
        super(id);
        this.initialBalance = initialBalance;
    }
}
