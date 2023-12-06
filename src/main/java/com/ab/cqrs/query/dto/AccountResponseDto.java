package com.ab.cqrs.query.dto;

import com.ab.cqrs.common.enums.AccountStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponseDto {
    private String accountId;
    private BigDecimal balance;
    private AccountStatus accountStatus;
}
