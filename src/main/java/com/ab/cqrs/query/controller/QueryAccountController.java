package com.ab.cqrs.query.controller;

import com.ab.cqrs.query.dto.*;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("queries/accounts")
public class QueryAccountController {

    private final QueryGateway queryGateway;

    @GetMapping("/{id}")
    public AccountResponseDto getAccount(@PathVariable String id) {
        var accountQueryDto = new GetAccountQueryDto(id);
        return queryGateway.query(accountQueryDto, AccountResponseDto.class).join();
    }

    @GetMapping
    public List<AccountResponseDto> getAll() {
        return queryGateway.query(new GetAllAccountsQueryDto(),
                ResponseTypes.multipleInstancesOf(AccountResponseDto.class)).join();
    }

    @GetMapping("/operations/{id}")
    public List<OperationResponseDto> getAccountOperations(@PathVariable String id) {
        return queryGateway.query(new GetAccountOperationsQueryDto(id),
                ResponseTypes.multipleInstancesOf(OperationResponseDto.class)).join();
    }

}
