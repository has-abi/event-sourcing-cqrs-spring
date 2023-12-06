package com.ab.cqrs.command.service;

import com.ab.cqrs.command.dto.CreateAccountDto;
import com.ab.cqrs.command.dto.CreditAccountDto;
import com.ab.cqrs.command.dto.DebitAccountDto;

import java.util.concurrent.CompletableFuture;

public interface CommandAccountService {
    CompletableFuture<String> createAccount(CreateAccountDto createAccountDto);

    CompletableFuture<String> creditAccount(CreditAccountDto creditAccountDto);

    CompletableFuture<String> debitAccount(DebitAccountDto debitAccountDto);
}
