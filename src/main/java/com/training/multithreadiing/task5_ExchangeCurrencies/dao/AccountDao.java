package com.training.multithreadiing.task5_ExchangeCurrencies.dao;

import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Account;

import java.io.IOException;

public interface AccountDao {
    Account getAccount(String accountId);

    void saveAccount(Account account);
}
