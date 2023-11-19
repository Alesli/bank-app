package com.domain.jmp.service.api;

import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.Subscription;
import com.domain.jmp.dto.User;

import java.util.List;
import java.util.Optional;

public interface Service {

    void subscribe(BankCard card);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);
    List<User> getAllUsers();
}
