package com.ab.cqrs.query.dto;

import com.ab.cqrs.common.enums.OperationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperationResponseDto {
    private Long id;
    private BigDecimal amount;
    private OperationType operationType;
    private Date operationDate;
}
