package com.training.multithreadiing.task5_ExchangeCurrencies.service;

import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Account;
import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Currency;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.AccountNotFoundException;

import java.math.BigDecimal;

public interface AccountService {

    void createAccount(Account account);

    void updateAccount(Account account);

    Account getAccount(String accountId) throws AccountNotFoundException;

    void updateAccountCurrencies(Account account, String fromCurrencyCode, String toCurrencyCode,
                                 BigDecimal amount, BigDecimal convertedAmount);
    Currency findCurrency(Account account, String currencyCode);
}

