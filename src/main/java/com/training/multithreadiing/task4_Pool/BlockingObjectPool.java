package com.training.multithreadiing.task4_Pool;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

@Slf4j
public class BlockingObjectPool {

    private final LinkedBlockingQueue<Object> pool;

    /**
     * Creates filled pool of passed size
     *
     * @param size of pool
     */
    public BlockingObjectPool(int size) {
        pool = new LinkedBlockingQueue<>(size);
        IntStream.range(0, size).forEach(i -> pool.offer(new Object()));
    }

    /**
     * Gets object from pool or blocks if pool is empty
     *
     * @return object from pool
     */
    public Object get() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted while trying to get an object from the pool", e);
            return null;
        }
    }

    /**
     * Puts object to pool or blocks if pool is full
     *
     * @param object to be taken back to pool
     */
    public void take(Object object) {
        try {
            pool.put(object);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Thread interrupted while trying to return an object to the pool", e);
        }
    }

}
