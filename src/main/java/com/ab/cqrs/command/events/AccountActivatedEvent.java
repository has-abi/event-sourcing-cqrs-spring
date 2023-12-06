package com.ab.cqrs.command.events;

import com.ab.cqrs.common.enums.AccountStatus;
import lombok.Getter;

public class AccountActivatedEvent extends BaseEvent<String> {
    @Getter
    private final AccountStatus accountStatus;

    public AccountActivatedEvent(String id, AccountStatus accountStatus) {
        super(id);
        this.accountStatus = accountStatus;
    }
}
