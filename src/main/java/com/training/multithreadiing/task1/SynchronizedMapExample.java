package com.training.multithreadiing.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

@Slf4j
public class SynchronizedMapExample {
    public static void main(String[] args) {
        // for a simpler, though less efficient, thread-safe alternative to HashMap
        Map<Integer, Integer> map = Collections.synchronizedMap(new HashMap<>());
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

