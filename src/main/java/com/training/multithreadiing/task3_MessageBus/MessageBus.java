package com.training.multithreadiing.task3_MessageBus;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class MessageBus {
    private final Map<String, List<String>> topics = new HashMap<>();

    public synchronized void postMessage(String topic, String message) {
        topics.putIfAbsent(topic, new ArrayList<>());
        topics.get(topic).add(message);
        notifyAll(); // Notify consumers waiting for messages
    }

    public synchronized String consumeMessage(String topic) {
        var messages = topics.get(topic);
        while (messages == null || messages.isEmpty()) {
            try {
                wait(); // Wait for a message to be posted
            } catch (InterruptedException e) {
                log.error("Thread interrupted", e);
                Thread.currentThread().interrupt();
            }
            messages = topics.get(topic);
        }
        return messages.remove(0);
    }
}

