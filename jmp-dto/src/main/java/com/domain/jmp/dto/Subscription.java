package com.domain.jmp.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Subscription {
    private String bankcard;
    private LocalDate startDate;
}
