package com.domain.jmp.cloud.bank.impl;

import com.domain.jmp.bank.api.BankService;
import com.domain.jmp.dto.*;

import java.security.SecureRandom;
import java.util.function.BiFunction;

public class BankServiceImpl implements BankService {
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        BiFunction<String, User, BankCard> cardConstructor = switch (type) {
            case CREDIT -> CreditBankCard::new;
            case DEBIT -> DebitBankCard::new;
        };
        return createCard(cardConstructor, user);
    }

    private BankCard createCard(BiFunction<String, User, BankCard> cardConstructor, User user) {
        var cardNumber = generateCardNumber();
        return cardConstructor.apply(cardNumber, user);
    }

    private String generateCardNumber() {
        var random = new SecureRandom();
        return String.format("%04d-%04d-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
    }
}