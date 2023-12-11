package com.training.multithreadiing.task6_ProducerConsumer.synchronizedBlock;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.List;

@Slf4j
public class ClassicModel {
    public static void main(String[] args) {
        List<Integer> queue = new LinkedList<>();
        var maxSize = 10;

        var consumer = new Consumer(queue);
        var producer = new Producer(queue, maxSize);

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
//                Thread.currentThread().interrupt();
            }

            var average = consumer.getAverage();
            var endTime = System.currentTimeMillis();
            var durationInSeconds = (endTime - startTime) / 1000.0;
            var opsPerSecond = consumer.getCount() / durationInSeconds;

            log.info("Average: {}", average);
            log.info("Total time (seconds): {}", durationInSeconds);
            log.info("Operations per second: {}", opsPerSecond);

//            [Thread-2] INFO  ClassicModel - Average: 49.52864737197383
//            [Thread-2] INFO  ClassicModel - Total time (seconds): 6.778
//            [Thread-2] INFO  ClassicModel - Operations per second: 196269.40100324582
        }));
    }
}

