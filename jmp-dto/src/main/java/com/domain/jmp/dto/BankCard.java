package com.domain.jmp.dto;

import lombok.*;

@Data
@AllArgsConstructor
public class BankCard {
    private String number;
    private User user;
}
