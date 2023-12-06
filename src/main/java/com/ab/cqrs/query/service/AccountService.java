package com.ab.cqrs.query.service;

import com.ab.cqrs.common.enums.OperationType;
import com.ab.cqrs.common.events.AccountActivatedEvent;
import com.ab.cqrs.common.events.AccountCreatedEvent;
import com.ab.cqrs.common.events.AccountCreditedEvent;
import com.ab.cqrs.common.events.AccountDebitedEvent;
import com.ab.cqrs.query.dto.GetAccountQueryDto;
import com.ab.cqrs.query.entity.Account;
import com.ab.cqrs.query.entity.Operation;
import com.ab.cqrs.query.mappers.AccountMapper;
import com.ab.cqrs.query.repository.AccountRepository;
import com.ab.cqrs.query.repository.OperationRepository;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryUpdateEmitter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final OperationRepository operationRepository;
    private final QueryUpdateEmitter queryUpdateEmitter;
    private final AccountMapper accountMapper;

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

    @EventHandler
    @Transactional
    public void onAccountCreditedEvent(AccountCreditedEvent accountCreditedEvent) {
        accountRepository.findById(accountCreditedEvent.getId()).ifPresent(account -> {
            account.setBalance(account.getBalance().add(accountCreditedEvent.getAmount()));

            Operation operation = new Operation();
            operation.setAmount(accountCreditedEvent.getAmount());
            operation.setOperationType(OperationType.CREDIT);
            operation.setAccount(account);

            operationRepository.save(operation);

            queryUpdateEmitter.emit(query -> ((GetAccountQueryDto) query.getPayload())
                            .getAccountId().equals(accountCreditedEvent.getId()),
                    accountMapper.accountToAccountResponseDto(account));
        });
    }

    @EventHandler
    @Transactional
    public void onAccountDebitedEvent(AccountDebitedEvent accountDebitedEvent) {
        accountRepository.findById(accountDebitedEvent.getId()).ifPresent(account -> {
            account.setBalance(account.getBalance().subtract(accountDebitedEvent.getAmount()));

            Operation operation = new Operation();
            operation.setAmount(accountDebitedEvent.getAmount());
            operation.setOperationType(OperationType.DEBIT);
            operation.setAccount(account);

            operationRepository.save(operation);

            queryUpdateEmitter.emit(query -> ((GetAccountQueryDto) query.getPayload())
                            .getAccountId().equals(accountDebitedEvent.getId()),
                    accountMapper.accountToAccountResponseDto(account));
        });
    }
}
