package com.domain.jmp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankCard {
    public BankCard(User user) {
        this.user = user;
    }

    private String number;
    private User user;
}
