package com.training.multithreadiing.task5_ExchangeCurrencies.service.impl;

import com.training.multithreadiing.task5_ExchangeCurrencies.service.ExchangeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Slf4j
@AllArgsConstructor
public class ExchangeServiceImpl implements ExchangeService {

    @Override
    public BigDecimal convertCurrency(BigDecimal amount, BigDecimal rate) {
        return amount.multiply(rate).setScale(2, RoundingMode.HALF_UP);
    }


}

