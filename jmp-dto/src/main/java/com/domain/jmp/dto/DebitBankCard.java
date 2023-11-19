package com.domain.jmp.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DebitBankCard extends BankCard {
    public DebitBankCard(User user) {
        super(user);
    }
}
