package com.ab.cqrs.command.exception;

public class NegativeInitialBalanceException extends RuntimeException {
    public NegativeInitialBalanceException(String message) {
        super(message);
    }
}
