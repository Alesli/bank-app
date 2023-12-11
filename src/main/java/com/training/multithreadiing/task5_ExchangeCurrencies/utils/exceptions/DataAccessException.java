package com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
