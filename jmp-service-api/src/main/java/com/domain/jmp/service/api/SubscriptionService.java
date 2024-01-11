package com.domain.jmp.service.api;

import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.Subscription;
import com.domain.jmp.dto.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface SubscriptionService {

    void subscribe(BankCard card);
    Optional<Subscription> getSubscriptionByBankCardNumber(String number);

    List<Subscription> getAllSubscriptionsByCondition(Predicate<Subscription> condition);
    List<User> getAllUsers();

    default double getAverageUsersAge() {
        return getAllUsers().stream()
                .mapToLong(user -> ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()))
                .average()
                .orElse(0.0);
    }

    static boolean isPayableUser(User user) {
        return ChronoUnit.YEARS.between(user.getBirthday(), LocalDate.now()) >= 18;
    }
}
