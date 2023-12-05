package com.ab.cqrs.command.events;

import com.ab.cqrs.command.enums.AccountStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountCreatedEvent extends BaseEvent<String>{

    private final BigDecimal balance;
    private final String currency;
    private final AccountStatus status;

    public AccountCreatedEvent(String id, BigDecimal balance, String currency, AccountStatus accountStatus) {
        super(id);
        this.balance = balance;
        this.currency = currency;
        this.status = accountStatus;
    }
}
