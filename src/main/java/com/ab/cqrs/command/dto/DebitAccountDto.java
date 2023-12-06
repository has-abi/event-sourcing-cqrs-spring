package com.ab.cqrs.command.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DebitAccountDto {
    private String accountId;
    private BigDecimal amount;
}
