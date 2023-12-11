package com.training.multithreadiing.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        /* Replace HashMap with ConcurrentHashMap for better concurrent performance
        without the risk of ConcurrentModificationException
         */
        Map<Integer, Integer> map = new ConcurrentHashMap<>();
        Thread writerThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                map.put(random.nextInt(1000), random.nextInt(100));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);
                }
            }
        });

        Thread readerThread = new Thread(() -> {
            while (true) {
                int sum = map.values().stream().mapToInt(Integer::intValue).sum();
                log.info("Current sum of values: " + sum);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);
                }
            }
        });

        writerThread.start();
        readerThread.start();
    }
}

