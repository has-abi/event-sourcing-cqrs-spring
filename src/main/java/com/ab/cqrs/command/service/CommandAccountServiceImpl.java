package com.ab.cqrs.command.service;

import com.ab.cqrs.command.commands.CreateAccountCommand;
import com.ab.cqrs.command.dto.CreateAccountDto;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RequiredArgsConstructor
@Service
public class CommandAccountServiceImpl implements CommandAccountService {

    private final CommandGateway commandGateway;

    @Override
    public CompletableFuture<String> createAccount(CreateAccountDto createAccountDto) {
        var createAccountCommand = new CreateAccountCommand(UUID.randomUUID().toString(),
                createAccountDto.getInitialBalance(), createAccountDto.getCurrency());

        return commandGateway.send(createAccountCommand);
    }
}
