package com.ab.cqrs.common.events;

import com.ab.cqrs.common.enums.AccountStatus;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class AccountCreatedEvent extends BaseEvent<String> {

    private final BigDecimal balance;
    private final AccountStatus status;

    public AccountCreatedEvent(String id, BigDecimal balance, AccountStatus accountStatus) {
        super(id);
        this.balance = balance;
        this.status = accountStatus;
    }
}
