package com.ab.cqrs.command.aggregate;

import com.ab.cqrs.command.commands.CreateAccountCommand;
import com.ab.cqrs.command.commands.CreditAccountCommand;
import com.ab.cqrs.command.commands.DebitAccountCommand;
import com.ab.cqrs.common.events.AccountActivatedEvent;
import com.ab.cqrs.common.events.AccountCreatedEvent;
import com.ab.cqrs.common.events.AccountCreditedEvent;
import com.ab.cqrs.common.events.AccountDebitedEvent;
import com.ab.cqrs.command.exception.InsufficientBalanceException;
import com.ab.cqrs.command.exception.NegativeAmountException;
import com.ab.cqrs.command.exception.NegativeInitialBalanceException;
import com.ab.cqrs.common.enums.AccountStatus;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
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
                AccountStatus.ACCOUNT_CREATED));
    }

    @EventSourcingHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("AccountCreatedEvent occurred");

        this.accountId = accountCreatedEvent.getId();
        this.balance = accountCreatedEvent.getBalance();
        this.status = accountCreatedEvent.getStatus();

        AggregateLifecycle.apply(new AccountActivatedEvent(this.accountId, AccountStatus.ACCOUNT_ACTIVATED));
    }

    @EventSourcingHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("AccountActivatedEvent occurred");

        this.status = accountActivatedEvent.getAccountStatus();
    }

    @CommandHandler
    public void handle(CreditAccountCommand creditAccountCommand) {
        log.info("CreditAccountCommand received");

        if (creditAccountCommand.getAmount().doubleValue() < 0) {
            throw new NegativeAmountException("Negative amount");
        }

        AggregateLifecycle.apply(new AccountCreditedEvent(creditAccountCommand.getId(),
                creditAccountCommand.getAmount()));

    }

    @EventSourcingHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("AccountCreditedEvent occurred");

        this.balance = this.balance.add(accountCreditedEvent.getAmount());
    }


    @CommandHandler
    public void handle(DebitAccountCommand debitAccountCommand) {
        log.info("DebitAccountCommand received");

        if (debitAccountCommand.getAmount().doubleValue() < 0) {
            throw new NegativeAmountException("Negative amount");
        }

        if (debitAccountCommand.getAmount().doubleValue() > this.balance.doubleValue()) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        AggregateLifecycle.apply(new AccountDebitedEvent(debitAccountCommand.getId(),
                debitAccountCommand.getAmount()));
    }

    @EventSourcingHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("AccountDebitedEvent occurred");

        this.balance = this.balance.subtract(accountDebitedEvent.getAmount());
    }
}
