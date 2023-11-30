package com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(String accountId) {
        super("Account with ID " + accountId + " not found.");
    }
}
