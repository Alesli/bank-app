package com.training.multithreadiing.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
public class HashMapExample {
    public static void main(String[] args) {
        Map<Integer, Integer> map = new HashMap<>();
        Thread writerThread = new Thread(() -> {
            var random = new Random();
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
                try {
                    int sum = map.values().stream().mapToInt(Integer::intValue).sum();
                    log.info("Current sum of values: " + sum);
                } catch (ConcurrentModificationException e) {
                    log.error("Caught ConcurrentModificationException", e);
                    break;
                }
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

