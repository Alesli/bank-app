package com.training.multithreadiing.task4_Pool;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String[] args) {
        var blockingObjectPool = new BlockingObjectPool(10);

        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    // Get an object from the pool
                    var object = blockingObjectPool.get();
                    log.info(Thread.currentThread().getName() + " got an object");

                    Thread.sleep(1000);

                    // Return the object to the pool
                    blockingObjectPool.take(object);
                    log.info(Thread.currentThread().getName() + " returned an object");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    log.error("Thread interrupted during execution", e);
                }
            }).start();
        }
    }
}
