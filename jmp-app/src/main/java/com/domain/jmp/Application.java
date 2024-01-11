package com.domain.jmp;


import com.domain.jmp.bank.api.BankService;
import com.domain.jmp.dto.BankCardType;
import com.domain.jmp.dto.User;
import com.domain.jmp.dto.exceptions.SubscriptionNotFoundException;
import com.domain.jmp.service.api.SubscriptionService;

import java.time.LocalDate;
import java.util.ServiceLoader;


public class Application {
    public static void main(String[] args) {

        var bankService = ServiceLoader.load(BankService.class).findFirst().orElseThrow();
        var subscriptionService = ServiceLoader.load(SubscriptionService.class).findFirst().orElseThrow();

        var user1 = new User("John", "Doe", LocalDate.of(1990, 10, 15));
        var user2 = new User("Alice", "Smith", LocalDate.of(2000, 8, 4));
        var user3 = new User("Bob", "Johnson", LocalDate.of(2010, 5, 21));
        var card1 = bankService.createBankCard(user1, BankCardType.DEBIT);
        var card2 = bankService.createBankCard(user2, BankCardType.CREDIT);
        var card3 = bankService.createBankCard(user3, BankCardType.DEBIT);
        subscriptionService.subscribe(card1);
        subscriptionService.subscribe(card2);
        subscriptionService.subscribe(card3);

        System.out.println("Subscribed User1: " + user1.getName());
        var subscriptionOptional = subscriptionService.getSubscriptionByBankCardNumber(card1.getNumber());
        if (subscriptionOptional.isPresent()) {
            var sub = subscriptionOptional.get();
            if (!sub.getStartDate().equals(LocalDate.now())) {
                throw new AssertionError("Start date does not match expected start date");
            }
            System.out.println("Subscription Start Date: " + sub.getStartDate());
        } else {
            throw new AssertionError("Subscription not found");
        }

        // Demonstrate getAllSubscriptionsByCondition
        var recentSubscriptions = subscriptionService.getAllSubscriptionsByCondition(
                sub -> sub.getStartDate().isAfter(LocalDate.now().minusMonths(1))
        );
        System.out.println("getAllSubscriptionsByCondition: " + recentSubscriptions.size());

        // Demonstrate getAllSubscriptionsByCondition
        var subscription = subscriptionService.getSubscriptionByBankCardNumber(card1.getNumber())
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        System.out.println("getSubscriptionByBankCardNumber: " + subscription.getStartDate());


        // Check if user1 is payable (over 18 years old)
        var isUser1Payable = SubscriptionService.isPayableUser(user1);
        System.out.println("Is User1 Payable? " + isUser1Payable); // Expected: true

        // Check if user2 is payable
        var isUser2Payable = SubscriptionService.isPayableUser(user2);
        System.out.println("Is User2 Payable? " + isUser2Payable); // Expected: true

        // Check if user3 is payable
        var isUser3Payable = SubscriptionService.isPayableUser(user3);
        System.out.println("Is User3 Payable? " + isUser3Payable); // Expected: false

        // Calculate the average age of all users
        double averageAge = subscriptionService.getAverageUsersAge();
        System.out.println("Average Users Age: " + averageAge);

        // Demonstrate exception handling
        try {
           var subscriptionEr = subscriptionService.getSubscriptionByBankCardNumber("non-existent")
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
            System.out.println("getSubscription: " + subscriptionEr.getStartDate());
        } catch (SubscriptionNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
