package com.ab.cqrs.command.service;

import com.ab.cqrs.command.commands.CreateAccountCommand;
import com.ab.cqrs.command.commands.CreditAccountCommand;
import com.ab.cqrs.command.commands.DebitAccountCommand;
import com.ab.cqrs.command.dto.CreateAccountDto;
import com.ab.cqrs.command.dto.CreditAccountDto;
import com.ab.cqrs.command.dto.DebitAccountDto;
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
                createAccountDto.getInitialBalance());

        return commandGateway.send(createAccountCommand);
    }

    @Override
    public CompletableFuture<String> creditAccount(CreditAccountDto creditAccountDto) {
        var creditAccountCommand = new CreditAccountCommand(creditAccountDto.getAccountId(), creditAccountDto.getAmount());
        return commandGateway.send(creditAccountCommand);
    }

    @Override
    public CompletableFuture<String> debitAccount(DebitAccountDto debitAccountDto) {
        var debitAccountCommand = new DebitAccountCommand(debitAccountDto.getAccountId(), debitAccountDto.getAmount());
        return commandGateway.send(debitAccountCommand);
    }
}
