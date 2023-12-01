package com.training.multithreadiing.task3_MessageBus;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class Producer implements Runnable {
    private final MessageBus bus;
    private final String topic;

    public Producer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            final var random = new Random();
            var message = "Message " + random.nextInt(100);
            bus.postMessage(topic, message);
            try {
                Thread.sleep(500); // to produce a message
            } catch (InterruptedException e) {
                log.error("Thread interrupted", e);
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

