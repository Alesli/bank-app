package com.domain.jmp;


import com.domain.jmp.bank.api.Bank;
import com.domain.jmp.dto.BankCard;
import com.domain.jmp.dto.BankCardType;
import com.domain.jmp.dto.Subscription;
import com.domain.jmp.dto.User;
import com.domain.jmp.dto.exceptions.SubscriptionNotFoundException;
import com.domain.jmp.service.api.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.ServiceLoader;

public class Application {
    public static void main(String[] args) {

        Bank bank = ServiceLoader.load(Bank.class).findFirst().orElseThrow();
        Service service = ServiceLoader.load(Service.class).findFirst().orElseThrow();

        User user1 = new User("John", "Doe", LocalDate.of(1990, 10, 15));
        User user2 = new User("Alice", "Smith", LocalDate.of(2000, 8, 4));
        User user3 = new User("Bob", "Johnson", LocalDate.of(2010, 5, 21));
        BankCard card1 = bank.createBankCard(user1, BankCardType.DEBIT);
        BankCard card2 = bank.createBankCard(user2, BankCardType.CREDIT);
        BankCard card3 = bank.createBankCard(user3, BankCardType.DEBIT);
        service.subscribe(card1);
        service.subscribe(card2);
        service.subscribe(card3);

        System.out.println("Subscribed User1: " + user1.getName());
        service.getSubscriptionByBankCardNumber(card1.getNumber())
                .ifPresent(sub -> System.out.println("Subscription Start Date: " + sub.getStartDate()));

        // Demonstrate getAllSubscriptionsByCondition
        List<Subscription> recentSubscriptions = service.getAllSubscriptionsByCondition(
                sub -> sub.getStartDate().isAfter(LocalDate.now().minusMonths(1))
        );
        System.out.println("getAllSubscriptionsByCondition: " + recentSubscriptions.size());

        // Demonstrate getAllSubscriptionsByCondition
        var subscription = service.getSubscriptionByBankCardNumber(card1.getNumber())
                .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        System.out.println("getSubscriptionByBankCardNumber: " + subscription.getStartDate());


        // Check if user1 is payable (over 18 years old)
        boolean isUser1Payable = Service.isPayableUser(user1);
        System.out.println("Is User1 Payable? " + isUser1Payable); // Expected: true

        // Check if user2 is payable
        boolean isUser2Payable = Service.isPayableUser(user2);
        System.out.println("Is User2 Payable? " + isUser2Payable); // Expected: true

        // Check if user3 is payable
        boolean isUser3Payable = Service.isPayableUser(user3);
        System.out.println("Is User3 Payable? " + isUser3Payable); // Expected: false

        // Calculate the average age of all users
        double averageAge = service.getAverageUsersAge();
        System.out.println("Average Users Age: " + averageAge);

        // Demonstrate exception handling
        try {
            service.getSubscriptionByBankCardNumber("non-existent")
                    .orElseThrow(() -> new SubscriptionNotFoundException("Subscription not found"));
        } catch (SubscriptionNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }
}
