package com.training.multithreadiing.task2;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *     Synchronization:
 *     The synchronized block is used on the shared list object.
 *     This ensures that only one thread can modify or read the list at a time, providing thread safety.
 *
 *     Deadlock Avoidance:
 *     There is no risk of deadlock in this code because there's only one shared resource (list),
 *     and it's always locked in the same order by all threads.
 *     Deadlocks typically occur when multiple locks are acquired in different orders by different threads.
 */
@Slf4j
public class ThreadSafeCalculations {
    public static void main(String[] args) {
        final List<Integer> list = new ArrayList<>();

        // Thread 1: Writes random numbers to the collection
        Thread writerThread = new Thread(() -> {
            Random random = new Random();
            while (true) {
                synchronized (list) {
                    list.add(random.nextInt(100));
                }
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);
                }
            }
        });

        // Thread 2: Calculates and prints the sum of the numbers
        Thread sumThread = new Thread(() -> {
            while (true) {
                int sum = 0;
                synchronized (list) {
                    for (int number : list) {
                        sum += number;
                    }
                }
                System.out.println("Sum: " + sum);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);
                }
            }
        });

        // Thread 3: Calculates and prints the square root of the sum of squares
        Thread squareRootThread = new Thread(() -> {
            while (true) {
                double sumOfSquares = 0.0;
                synchronized (list) {
                    for (int number : list) {
                        sumOfSquares += Math.pow(number, 2);
                    }
                }
                System.out.println("Square root of sum of squares: " + Math.sqrt(sumOfSquares));
                try {
                    Thread.sleep(130);
                } catch (InterruptedException e) {
                    log.error("Writer thread interrupted", e);
                }
            }
        });

        writerThread.start();
        sumThread.start();
        squareRootThread.start();
    }
}

