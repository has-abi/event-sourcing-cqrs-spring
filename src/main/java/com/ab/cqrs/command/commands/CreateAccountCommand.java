package com.ab.cqrs.command.commands;

import lombok.Getter;

import java.math.BigDecimal;

public class CreateAccountCommand extends BaseCommand<String>{
    @Getter
    private final BigDecimal initialBalance;
    @Getter
    private final String currency;

    public CreateAccountCommand(String id, BigDecimal initialBalance, String currency) {
        super(id);
        this.initialBalance = initialBalance;
        this.currency = currency;
    }
}
