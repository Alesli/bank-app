package com.domain.jmp.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class CreditBankCard extends BankCard {
    public CreditBankCard(String number, User user) {
        super(number, user);
    }
}
