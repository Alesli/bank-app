package com.training.multithreadiing.task3_ProducerConsume;

public class Main {
    public static void main(String[] args) {
        var messageBus = new MessageBus();
        var topic = "testTopic";

        for (int i = 0; i < 3; i++) {
            var producer = new Producer(messageBus, topic);
            new Thread(producer).start();
        }

        for (int i = 0; i < 3; i++) {
            var consumer = new Consumer(messageBus, topic);
            new Thread(consumer).start();
        }
    }
}

