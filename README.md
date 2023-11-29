## 2. Advanced Multithreading (`multithreading` branch)
Exploring the depths of concurrency and multithreading in Java. This module includes practical tasks to enhance understanding and skills in multithreading and concurrent programming.

#### Task 1 - Concurrent Map Usage
- **Objective**: Work with `HashMap<Integer, Integer>`. Implement two threads: one adds elements to the map, and the other sums the values. Handle `ConcurrentModificationException`.
- **Goals**:
    1. Fix the problem using `ConcurrentHashMap` and `Collections.synchronizedMap()`.
    2. Analyze what happens after replacing a simple Map implementation.
    3. Implement a custom `ThreadSafeMap` with and without synchronization.
    4. Test performance across Java versions 6, 8, 10, and 11.
    5. Provide a report on findings.

#### Task 2 - Deadlocks
- **Objective**: Create three threads to perform different operations on a collection safely.
- **Details**:
    1. 1st thread writes random numbers to a collection indefinitely.
    2. 2nd thread prints the sum of the numbers.
    3. 3rd thread prints the square root of the sum of squares of all numbers.
- **Goal**: Implement thread safety using synchronization blocks and resolve potential deadlocks.

#### Task 3 - Message Bus with Producer-Consumer Pattern
- **Objective**: Implement an asynchronous message bus.
- **Requirements**:
    1. Do not use queue implementations from `java.util.concurrent`.
    2. Implement a producer to generate and post random messages.
    3. Implement a consumer to process messages on a specific topic and log them.
    4. (Optional) Run multiple consumers and producers in parallel.

#### Task 4 - Object Pool in Multithreaded Environment
- **Objective**: Create a simple object pool with multithreading support.
- **Implementation**: Develop a `BlockingObjectPool` class with methods to get and return objects, handling blocking when the pool is empty or full.

#### Task 5 - Currency Exchange Application
- **Objective**: Develop an application for currency exchange operations.
- **Features**:
    1. Model entities for currencies, user accounts, and exchange rates.
    2. Store user account data in files.
    3. Implement DAO, service, and utility layers.
    4. Provide high-level operations for managing accounts and exchange rates.
    5. Create sample data for accounts and currencies.
    6. Ensure thread-safe operations for concurrent access to accounts.
    7. Use `ExecutorService` for thread management.
    8. Implement custom exceptions for error reporting.
    9. Validate inputs and account statuses.
    10. Log activities and conversion results.

#### Task 6 (Optional) - Producer-Consumer Console Application
- **Objective**: Create a multi-threaded application with producer and consumer threads.
- **Details**:
    1. Implement using a non-blocking queue and synchronized blocks (`wait()`/`notify()`).
    2. Reimplement using `java.util.concurrent` synchronization tools.
    3. Compare performance of both implementations in operations per second.
    4. Ensure graceful shutdown and correct finalization of threads.
