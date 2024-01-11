package com.domain.jmp.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class User {
    private String name;
    private String surname;
    private LocalDate birthday;
}
