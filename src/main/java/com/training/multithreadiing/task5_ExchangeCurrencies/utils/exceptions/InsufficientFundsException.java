package com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String currency) {
        super("Insufficient funds in currency: " + currency);
    }
}

