package com.domain.jmp.dto;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class DebitBankCard extends BankCard {
    public DebitBankCard(String number, User user) {
        super(number, user);
    }
}
