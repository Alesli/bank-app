package com.training.multithreadiing.task5_ExchangeCurrencies.dao;

import com.training.multithreadiing.task5_ExchangeCurrencies.dto.Account;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.FileUtil;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.JsonUtil;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.AccountNotFoundException;
import com.training.multithreadiing.task5_ExchangeCurrencies.utils.exceptions.DataAccessException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

@Slf4j
@AllArgsConstructor
public class FileAccountDaoImpl implements AccountDao {
    private final String storagePath;

    @Override
    public Account getAccount(String accountId) {
        var file = new File(storagePath + accountId + ".json");
        if (!file.exists()) {
            throw new AccountNotFoundException(accountId);
        }
        String content;
        try {
            content = FileUtil.readFileToString(file);
            return JsonUtil.fromJson(content, Account.class);
        } catch (IOException e) {
            log.error("Error processing account data for " + accountId, e);
            throw new DataAccessException("Error processing account data for " + accountId, e);
        }
    }

    @Override
    public void saveAccount(Account account) {
        try {
            var file = new File(storagePath + account.getAccountId() + ".json");
            String json = JsonUtil.toJson(account);
            FileUtil.writeStringToFile(file, json);
        } catch (IOException e) {
            log.error("Error processing account data for " + account.getAccountId(), e);
            throw new DataAccessException("Error processing account data for " + account.getAccountId(), e);
        }
    }
}

