package com.ab.cqrs.command.dto;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CreateAccountDto {
    private BigDecimal initialBalance;
    private String currency;
}
