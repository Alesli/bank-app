package com.training.multithreadiing.task1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;
import java.util.Random;

/** The best performance than using a synchronized method.
ConcurrentHashMap in versions Java 8, 11 is highly optimized for concurrency.
 */
@Slf4j
public class ThreadSafeMapWithoutSync<K, V> {
    private final ConcurrentHashMap<K, V> map = new ConcurrentHashMap<>();

    public void put(K key, V value) {
        map.put(key, value);
    }

    public V get(K key) {
        return map.get(key);
    }

    public static void main(String[] args) {
        ThreadSafeMapWithoutSync<Integer, Integer> map = new ThreadSafeMapWithoutSync<>();
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
                int sum = map.map.values().stream().mapToInt(Integer::intValue).sum();
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

