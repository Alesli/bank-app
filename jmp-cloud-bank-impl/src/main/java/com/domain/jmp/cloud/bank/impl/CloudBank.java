package com.domain.jmp.cloud.bank.impl;

import com.domain.jmp.bank.api.Bank;
import com.domain.jmp.dto.*;

public class CloudBank implements Bank {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        return switch (type) {
            case CREDIT -> new CreditBankCard(user);
            case DEBIT -> new DebitBankCard(user);
        };
    }
}
