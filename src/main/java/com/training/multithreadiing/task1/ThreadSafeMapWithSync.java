package com.training.multithreadiing.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/** for Java 8, 11: Improved performance due to better JVM optimizations and enhancements.
    However, explicit synchronization might still cause contention under heavy load.
 */
@Slf4j
public class ThreadSafeMapWithSync<K, V> {
    private final Map<K, V> map = new HashMap<>();

    public synchronized void put(K key, V value) {
        map.put(key, value);
    }
    public synchronized Collection<V> values() {
        return new HashMap<>(map).values();
    }

    public static void main(String[] args) {
        ThreadSafeMapWithSync<Integer, Integer> map = new ThreadSafeMapWithSync<>();
        Thread writerThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                map.put(random.nextInt(10), random.nextInt(100));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);

                }
            }
        });

        Thread readerThread = new Thread(() -> {
            while (true) {
                int sum = 0;
                synchronized (map) {
                    for (Integer value : map.values()) {
                        sum += value;
                    }
                }
                System.out.println("Current sum of values: " + sum);

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

