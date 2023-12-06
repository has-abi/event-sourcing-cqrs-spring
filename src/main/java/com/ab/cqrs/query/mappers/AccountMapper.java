package com.ab.cqrs.query.mappers;

import com.ab.cqrs.query.dto.AccountResponseDto;
import com.ab.cqrs.query.dto.OperationResponseDto;
import com.ab.cqrs.query.entity.Account;
import com.ab.cqrs.query.entity.Operation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface AccountMapper {
    @Mapping(target = "accountId", source = "id")
    AccountResponseDto accountToAccountResponseDto(Account account);

    @Mapping(target = "operationDate", source = "createdOn")
    OperationResponseDto operationToOperationResponseDto(Operation operation);
}
