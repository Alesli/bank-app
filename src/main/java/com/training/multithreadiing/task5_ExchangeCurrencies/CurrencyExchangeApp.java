package com.training.multithreadiing.task5_ExchangeCurrencies;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.training.multithreadiing.task5_ExchangeCurrencies.dao.FileAccountDaoImpl;
import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Account;
import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Currency;
import com.training.multithreadiing.task5_ExchangeCurrencies.service.impl.AccountServiceImpl;
import com.training.multithreadiing.task5_ExchangeCurrencies.service.impl.ExchangeServiceImpl;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

@Slf4j
public class CurrencyExchangeApp {
    public static void main(String[] args) {
        var objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        var exchangeRate = new BigDecimal("1.085"); // rate: 1 USD = 1.085 EUR

        var storagePath = "/Users/Alesia_Skarakhod/Desktop/JIRA_LA-1948/";
        var accountDao = new FileAccountDaoImpl(storagePath);
        var accountService = new AccountServiceImpl(accountDao);
        var exchangeService = new ExchangeServiceImpl();
        var currencyExchange = new CurrencyExchange(accountService, exchangeService);

        for (int i = 1; i <= 5; i++) {
            String accountId = "account" + i;
            var random = new Random();
            var account = new Account(accountId, List.of(
                    new Currency("USD", new BigDecimal("1000.00")),
                    new Currency("EUR", new BigDecimal("800.00"))
            ));
            accountService.createAccount(account);
            var amount = new BigDecimal(100 + random.nextInt(1000));
            currencyExchange.executeExchange(accountId, "USD", "EUR", amount, exchangeRate);
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        for (int i = 1; i <= 5; i++) {
            String accountId = "account" + i;
            var updatedAccount = accountService.getAccount(accountId);
            updatedAccount.getCurrencies().forEach(currency ->
                    log.info("Account ID: " + accountId + " - Currency: " + currency.getCode() + ", Amount: " + currency.getValue()));

        }
        currencyExchange.shutdown();
    }
}

