package com.domain.jmp.cloud.bank.impl;

import com.domain.jmp.bank.api.Bank;
import com.domain.jmp.dto.*;

import java.util.Random;
import java.util.function.BiFunction;

public class BankImpl implements Bank {

    private final Random random = new Random();
    @Override
    public BankCard createBankCard(User user, BankCardType type) {
        BiFunction<String, User, BankCard> cardConstructor = switch (type) {
            case CREDIT -> CreditBankCard::new;
            case DEBIT -> DebitBankCard::new;
        };
        return createCard(cardConstructor, user);
    }

    private BankCard createCard(BiFunction<String, User, BankCard> cardConstructor, User user) {
        String cardNumber = generateCardNumber();
        return cardConstructor.apply(cardNumber, user);
    }

    private String generateCardNumber() {
        return String.format("%04d-%04d-%04d-%04d",
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000),
                random.nextInt(10000));
    }
}
