package com.ab.cqrs.command.controller;

import com.ab.cqrs.command.dto.CreateAccountDto;
import com.ab.cqrs.command.dto.CreditAccountDto;
import com.ab.cqrs.command.dto.DebitAccountDto;
import com.ab.cqrs.command.service.CommandAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/accounts")
@RequiredArgsConstructor
public class CommandAccountController {

    private final CommandAccountService accountService;

    @PostMapping("/create")
    public CompletableFuture<String> createAccount(CreateAccountDto createAccountDto) {
        return accountService.createAccount(createAccountDto);
    }

    @PutMapping("/credit")
    public CompletableFuture<String> creditAccount(CreditAccountDto creditAccountDto) {
        return accountService.creditAccount(creditAccountDto);
    }

    @PutMapping("/debit")
    public CompletableFuture<String> debitAccount(DebitAccountDto debitAccountDto) {
        return accountService.debitAccount(debitAccountDto);
    }
}
