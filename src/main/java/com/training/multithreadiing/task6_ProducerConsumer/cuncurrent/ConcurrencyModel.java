package com.training.multithreadiing.task6_ProducerConsumer.cuncurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
public class ConcurrencyModel {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        var operationCount = new AtomicInteger(0);

        var producer = new Producer(blockingQueue, operationCount);
        var consumer = new Consumer(blockingQueue, operationCount);

        var producerThread = new Thread(producer);
        var consumerThread = new Thread(consumer);

        var startTime = System.currentTimeMillis();

        producerThread.start();
        consumerThread.start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("Shutdown initiated.");
            producerThread.interrupt();
            consumerThread.interrupt();

            try {
                producerThread.join();
                consumerThread.join();
            } catch (InterruptedException e) {
                log.error("Main thread interrupted during shutdown", e);
                Thread.currentThread().interrupt();
            }

            var totalOperations = operationCount.get();
            var endTime = System.currentTimeMillis();
            var durationInSeconds = (endTime - startTime) / 1000.0;
            var opsPerSecond = totalOperations / durationInSeconds;

            log.info("Total operations: {}", totalOperations);
            log.info("Total time (seconds): {}", durationInSeconds);
            log.info("Operations per second: {}", opsPerSecond);

//            Thread-2] INFO  ConcurrencyModel - Total operations: 1911610
//            [Thread-2] INFO  ConcurrencyModel - Total time (seconds): 4.485
//            [Thread-2] INFO  ConcurrencyModel - Operations per second: 426222.9654403567
        }));
    }
}

