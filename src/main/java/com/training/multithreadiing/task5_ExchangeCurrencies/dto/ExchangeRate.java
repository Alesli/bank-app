package com.training.multithreadiing.task5_ExchangeCurrencies.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeRate {
    private String fromCurrency;
    private String toCurrency;
    private BigDecimal rate;

}
