package com.ab.cqrs.query.service;

import com.ab.cqrs.command.events.AccountActivatedEvent;
import com.ab.cqrs.command.events.AccountCreatedEvent;
import com.ab.cqrs.query.entity.Account;
import com.ab.cqrs.query.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @EventHandler
    @Transactional
    public void onAccountCreatedEvent(AccountCreatedEvent accountCreatedEvent) {
        Account account = new Account();

        account.setId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setAccountStatus(accountCreatedEvent.getStatus());

        accountRepository.save(account);
    }

    @EventHandler
    @Transactional
    public void onAccountActivatedEvent(AccountActivatedEvent accountActivatedEvent) {
        accountRepository.findById(accountActivatedEvent.getId()).ifPresent(account -> {
            account.setAccountStatus(accountActivatedEvent.getAccountStatus());
        });
    }
}
