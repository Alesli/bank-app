package com.training.multithreadiing.task5_ExchangeCurrencies.service;

import java.math.BigDecimal;

public interface ExchangeService {
    BigDecimal convertCurrency(BigDecimal amount, BigDecimal rate);
}
