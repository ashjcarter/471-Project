package PRODUCER_CONSUMER;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

class Producer implements Runnable {
    private final Queue<Integer> buffer;
    private final Semaphore empty;
    private final Semaphore full;
    private final ReentrantLock mutex;

    public Producer(Queue<Integer> buffer, Semaphore empty, Semaphore full, ReentrantLock mutex) {
        this.buffer = buffer;
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                empty.acquire();
                mutex.lock();
                buffer.add(i);
                System.out.println("Produced: " + i);
                mutex.unlock();
                full.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Consumer implements Runnable {
    private final Queue<Integer> buffer;
    private final Semaphore empty;
    private final Semaphore full;
    private final ReentrantLock mutex;

    public Consumer(Queue<Integer> buffer, Semaphore empty, Semaphore full, ReentrantLock mutex) {
        this.buffer = buffer;
        this.empty = empty;
        this.full = full;
        this.mutex = mutex;
    }

    @Override
    public void run() {
        while (true) {
            try {
                full.acquire();
                mutex.lock();
                Integer item = buffer.poll();
                if (item == null) {
                    break;
                }
                System.out.println("Consumed: " + item);
                mutex.unlock();
                empty.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
public class Main {
    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide 3 arguments: sleep time, number of producers, number of consumers");
            return;
        }

        int sleepTime = Integer.parseInt(args[0]);
        int numProducers = Integer.parseInt(args[1]);
        int numConsumers = Integer.parseInt(args[2]);

        int bufferSize = 10;
        Queue<Integer> buffer = new LinkedList<>();

        Semaphore empty = new Semaphore(bufferSize); // initially, buffer is empty
        Semaphore full = new Semaphore(0); // initially, buffer is not full
        ReentrantLock mutex = new ReentrantLock();

        for (int i = 0; i < numProducers; i++) {
            new Thread(new Producer(buffer, empty, full, mutex)).start();
        }

        for (int i = 0; i < numConsumers; i++) {
            new Thread(new Consumer(buffer, empty, full, mutex)).start();
        }

        try {
            Thread.sleep(sleepTime * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.exit(0);
    }
}