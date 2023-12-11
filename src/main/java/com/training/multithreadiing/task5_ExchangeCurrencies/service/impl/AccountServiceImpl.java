package com.training.multithreadiing.task5_ExchangeCurrencies.service.impl;

import com.training.multithreadiing.task5_ExchangeCurrencies.dao.AccountDao;
import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Account;
import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Currency;
import com.training.multithreadiing.task5_ExchangeCurrencies.service.AccountService;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.AccountNotFoundException;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.InsufficientFundsException;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.InvalidCurrencyException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;

@Slf4j
@AllArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountDao accountDao;

    @Override
    public void createAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public Account getAccount(String accountId) throws AccountNotFoundException {
        var account = accountDao.getAccount(accountId);
        if (account == null) {
            log.error("Account not found for id: " + accountId);
            throw new AccountNotFoundException(accountId);
        }
        return account;
    }

    @Override
    public void updateAccount(Account account) {
        accountDao.saveAccount(account);
    }

    @Override
    public void updateAccountCurrencies(Account account, String fromCurrencyCode, String toCurrencyCode,
                                        BigDecimal amount, BigDecimal convertedAmount) {
        try {
            var fromCurrency = findCurrency(account, fromCurrencyCode);
            if (fromCurrency == null) {
                log.error("Source currency not found: {}", fromCurrencyCode);
                throw new InvalidCurrencyException(fromCurrencyCode);
            }
            if (fromCurrency.getValue().compareTo(amount) < 0) {
                log.error("Insufficient funds in source currency: {}", fromCurrencyCode);
                throw new InsufficientFundsException(fromCurrencyCode);
            }
            fromCurrency.setValue(fromCurrency.getValue().subtract(amount));

            var toCurrency = findCurrency(account, toCurrencyCode);
            if (toCurrency == null) {
                account.getCurrencies().add(new Currency(toCurrencyCode, convertedAmount));
            } else {
                toCurrency.setValue(toCurrency.getValue().add(convertedAmount));
            }
        } catch (InvalidCurrencyException e) {
            log.error("Error processing currency exchange", e);
        }
    }

    @Override
    public Currency findCurrency(Account account, String currencyCode) {
        return account.getCurrencies().stream()
                .filter(currency -> currency.getCode().equals(currencyCode))
                .findFirst()
                .orElse(null);
    }
}

