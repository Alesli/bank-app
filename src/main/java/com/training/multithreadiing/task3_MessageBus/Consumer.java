package com.training.multithreadiing.task3_MessageBus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Consumer implements Runnable {
    private final MessageBus bus;
    private final String topic;

    public Consumer(MessageBus bus, String topic) {
        this.bus = bus;
        this.topic = topic;
    }

    @Override
    public void run() {
        while (true) {
            var message = bus.consumeMessage(topic);
            log.info("Consumed message: {}", message);
        }
    }
}

