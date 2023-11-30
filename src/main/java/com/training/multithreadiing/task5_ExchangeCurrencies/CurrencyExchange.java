package com.training.multithreadiing.task5_ExchangeCurrencies;

import com.training.multithreadiing.task5_ExchangeCurrencies.service.AccountService;
import com.training.multithreadiing.task5_ExchangeCurrencies.service.ExchangeService;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.AccountNotFoundException;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.InsufficientFundsException;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.InvalidCurrencyException;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
public class CurrencyExchange {
    private final AccountService accountService;
    private final ExchangeService exchangeService;
    private final ExecutorService executorService;

    public CurrencyExchange(AccountService accountService, ExchangeService exchangeService) {
        this.accountService = accountService;
        this.exchangeService = exchangeService;
        this.executorService = Executors.newFixedThreadPool(10);
    }

    public void executeExchange(String accountId, String fromCurrencyCode, String toCurrencyCode,
                                BigDecimal amount, BigDecimal rate) {
        Runnable task = () -> {
            try {
                var account = accountService.getAccount(accountId);
                if (account == null) {
                    log.error("Account not found: {}", accountId);
                    throw new AccountNotFoundException(accountId);
                }
                synchronized (account) {
                    log.info("Starting exchange for account: {}", accountId);
                    var convertedAmount = exchangeService.convertCurrency(amount, rate);
                    log.info("Currency converted. Amount: {}; Rate: {}", amount, rate);

                    accountService.updateAccountCurrencies(account, fromCurrencyCode, toCurrencyCode, amount, convertedAmount);
                    log.info("Account currencies updated for account: {}", accountId);

                    accountService.updateAccount(account);
                    log.info("Account updated: {}", accountId);
                    log.info("Exchange completed for account: {}", accountId);
                }
            } catch (InsufficientFundsException | AccountNotFoundException | InvalidCurrencyException e) {
                log.error("Error processing currency exchange for account " + accountId, e);
            }
        };
        executorService.submit(task);
    }

    public void shutdown() {
        executorService.shutdown();
    }
}

