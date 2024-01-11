package com.domain.jmp.cloud.service.impl;

import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.Subscription;
import com.domain.jmp.dto.User;
import com.domain.jmp.service.api.SubscriptionService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class CloudServiceImpl implements SubscriptionService {

    private final Map<String, Subscription> subscriptions = new ConcurrentHashMap<>();
    private final List<User> users = new ArrayList<>();

    @Override
    public void subscribe(BankCard bankCard) {
        var subscription = new Subscription(bankCard.getNumber(), LocalDate.now());
        subscriptions.put(bankCard.getNumber(), subscription);
        addUser(bankCard);
    }

    private void addUser(BankCard bankCard) {
        users.add(bankCard.getUser());
    }

    @Override
    public Optional<Subscription> getSubscriptionByBankCardNumber(String number) {
        return Optional.ofNullable(subscriptions.get(number));
    }

    @Override
    public List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition) {
        return subscriptions.values().stream()
                .filter(condition)
                .collect(Collectors.toUnmodifiableList());
    }


    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users);
    }
}