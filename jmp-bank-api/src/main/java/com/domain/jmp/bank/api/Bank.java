package com.domain.jmp.bank.api;

import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.BankCardType;
import com.domain.jmp.dto.User;

public interface Bank {
    BankCard createBankCard(User user, BankCardType type);
}
