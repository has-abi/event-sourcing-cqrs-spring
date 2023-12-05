package com.ab.cqrs.command.controller;

import com.ab.cqrs.command.dto.CreateAccountDto;
import com.ab.cqrs.command.service.CommandAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/command/accounts")
@RequiredArgsConstructor
public class CommandAccountController {

    private final CommandAccountService accountService;

    @PostMapping
    public ResponseEntity<CompletableFuture<String>> createAccount(CreateAccountDto createAccountDto) {
        return new ResponseEntity<>(accountService.createAccount(createAccountDto), HttpStatus.CREATED);
    }
}
