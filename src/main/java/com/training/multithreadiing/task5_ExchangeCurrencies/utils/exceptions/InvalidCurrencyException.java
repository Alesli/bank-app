package com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions;

public class InvalidCurrencyException extends RuntimeException {
    public InvalidCurrencyException(String currency) {
        super("Invalid currency: " + currency);
    }
}
