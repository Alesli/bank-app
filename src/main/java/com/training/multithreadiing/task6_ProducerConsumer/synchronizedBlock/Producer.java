package com.training.multithreadiing.task6_ProducerConsumer.synchronizedBlock;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Random;

@Slf4j
@AllArgsConstructor
public class Producer implements Runnable {
    private final List<Integer> queue;
    private final int maxSize;

    @Override
    public void run() {
        var random = new Random();
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (queue) {
                try {
                    while (queue.size() == maxSize) {
                        log.info("Queue is full. Producer is waiting.");
                        queue.wait();
                    }
                    var number = random.nextInt(100);
                    queue.add(number);
                    log.info("Produced: {}", number);
                    queue.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.info("Producer thread was interrupted and is now stopping");
                    break;
                }
            }
        }
        log.info("Producer stopped.");
    }
}

