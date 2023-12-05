package com.ab.cqrs.command.service;

import com.ab.cqrs.command.dto.CreateAccountDto;

import java.util.concurrent.CompletableFuture;

public interface CommandAccountService {
    CompletableFuture<String> createAccount(CreateAccountDto createAccountDto);
}
