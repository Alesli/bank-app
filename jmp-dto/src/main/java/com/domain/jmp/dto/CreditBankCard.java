package com.domain.jmp.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CreditBankCard extends BankCard {
    public CreditBankCard(User user) {
        super(user);
    }
}
