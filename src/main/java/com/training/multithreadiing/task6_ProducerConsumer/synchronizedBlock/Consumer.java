package com.training.multithreadiing.task6_ProducerConsumer.synchronizedBlock;

import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class Consumer implements Runnable {
    private final List<Integer> queue;
    private final AtomicLong total = new AtomicLong(0);
    private final AtomicInteger count = new AtomicInteger(0);

    public Consumer(List<Integer> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (queue) {
                try {
                    while (queue.isEmpty()) {
                        log.info("Queue is empty. Consumer is waiting.");
                        queue.wait();
                    }
                    var number = queue.remove(0);
                    total.addAndGet(number);
                    count.incrementAndGet();
                    log.info("Consumed: {}", number);
                    queue.notifyAll();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.info("Consumer thread was interrupted and is now stopping");
                    break;
                }
            }
        }
        log.info("Consumer stopped. Total consumed: {}, Average: {}", count.get(), getAverage());
    }

    public double getAverage() {
        return count.get() > 0 ? (double) total.get() / count.get() : 0;
    }

    public int getCount() {
        return count.get();
    }

}

