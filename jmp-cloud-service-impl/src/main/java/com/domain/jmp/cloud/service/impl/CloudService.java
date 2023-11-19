package com.domain.jmp.cloud.service.impl;

import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.Subscription;
import com.domain.jmp.dto.User;
import com.domain.jmp.service.api.Service;

import java.util.*;
import java.util.stream.Collectors;

public class CloudService implements Service {

    private final Map<String, Subscription> subscriptions = new HashMap<>();
    private final List<User> users = new ArrayList<>();

    @Override
    public void subscribe(BankCard card) {
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.ofNullable(subscriptions.get(number));
    }

    @Override
    public List<User> getAllUsers() {
        return users.stream().collect(Collectors.toList());
    }
}