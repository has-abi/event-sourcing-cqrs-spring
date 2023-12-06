package com.ab.cqrs.query.entity;

import com.ab.cqrs.common.enums.AccountStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Account {
    @Id
    private String id;
    private BigDecimal balance;
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;
}
