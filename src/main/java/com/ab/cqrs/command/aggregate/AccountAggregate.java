package com.ab.cqrs.command.aggregate;

import com.ab.cqrs.command.commands.CreateAccountCommand;
import com.ab.cqrs.common.enums.AccountStatus;
import com.ab.cqrs.command.events.AccountActivatedEvent;
import com.ab.cqrs.command.events.AccountCreatedEvent;
import com.ab.cqrs.command.exception.NegativeInitialBalanceException;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Slf4j
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private BigDecimal balance;
    private String currency;
    private AccountStatus status;

    public AccountAggregate() {
        // Required by Axon
    }

    @CommandHandler
    public AccountAggregate(CreateAccountCommand createAccountCommand) {
        log.info("CreateAccountCommand received");

        if (createAccountCommand.getInitialBalance().doubleValue() < 0) {
            throw new NegativeInitialBalanceException("Negative initial balance");
        }

        AggregateLifecycle.apply(new AccountCreatedEvent(createAccountCommand.getId(),
                createAccountCommand.getInitialBalance(),
                createAccountCommand.getCurrency(),
                AccountStatus.ACCOUNT_CREATED));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("AccountCreatedEvent occurred");

        this.accountId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.currency = accountCreatedEvent.getCurrency();
        this.status = accountCreatedEvent.getStatus();

        AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, AccountStatus.ACCOUNT_ACTIVATED));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("AccountActivatedEvent occurred");

        this.status = accountActivatedEvent.getAccountStatus();
    }
}
