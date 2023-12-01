package com.training.multithreadiing.task6_ProducerConsumer.cuncurrent;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@AllArgsConstructor
public class Producer implements Runnable {
    private final BlockingQueue<Integer> queue;
    private final AtomicInteger operationCount;

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                var data = new Random().nextInt(100);
                queue.put(data);
                operationCount.incrementAndGet();
                log.info("Produced: {}", data);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                log.info("Producer thread was interrupted and is now stopping");
                break;
            }
        }
        log.info("Producer stopped.");
    }
}

