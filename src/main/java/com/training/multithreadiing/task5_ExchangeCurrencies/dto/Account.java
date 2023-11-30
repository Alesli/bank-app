package com.training.multithreadiing.task5_ExchangeCurrencies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    private String accountId;
    private List<Currency> currencies;
}
