package com.ab.cqrs.command.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BaseCommand <T>{
    @TargetAggregateIdentifier
    private T id;
}
