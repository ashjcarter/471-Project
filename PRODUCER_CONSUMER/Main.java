package PRODUCER_CONSUMER;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main
{
    private static final BlockingQueue<Item> buffer = new LinkedBlockingQueue<>();
    private static final AtomicBoolean runThreads = new AtomicBoolean(true);
    private static final Random rand = new Random();
    private static int sleepTime;
    private static long totalTurnaroundTime = 0;
    private static int totalItems = 0;

    private static void producer()
    {
        // Logic to produce items and insert into buffer
        while(runThreads.get()) 
        {
            try
            {
                System.out.println("Sleeping for " + sleepTime);
                Thread.sleep(rand.nextInt(sleepTime) * 1000);
                int value = rand.nextInt(10);
                Item item = new Item(value);
                buffer.put(item);

                System.out.println("Producer produced " + item.getValue());
            }
            catch(InterruptedException e)
            {
                break;
            }
        }
    }

    private static void consumer()
    {
        // Logic to consume items and remove from buffer
        while(runThreads.get() || !buffer.isEmpty()) 
        {
            try
            {
                Item item = buffer.take();
                long turnaroundTime = System.currentTimeMillis() - item.getProductionTime();
                totalTurnaroundTime += turnaroundTime;
                totalItems++;
                System.out.println("Consumed item: " + item.getValue() + 
                " Turnaround time: " + turnaroundTime);

            }
            catch(InterruptedException e)
            {
                break;
            }

        }
    }

    public static void runThreads(int sleepTime, int numProducers, int numConsumers)
    {
        Main.sleepTime = sleepTime;
        runThreads.set(true);
        List<Thread> threads = new ArrayList<>();

        for(int i = 0; i < numProducers; i++)
        {
            Thread producerThread = new Thread(Main::producer);
            producerThread.start();
            threads.add(producerThread);
        }

        for(int j = 0; j < numConsumers; j++)
        {
            Thread consumerThread = new Thread(Main::consumer);
            consumerThread.start();
            threads.add(consumerThread);
        }

        try
        {
            Thread.sleep(sleepTime * 1000);
            runThreads.set(false);

            for(Thread thread : threads)
            {
                thread.interrupt();
            }

            for(Thread thread : threads)
            {
                thread.join();
            }

            double avgTurnaroundTime = (double)totalTurnaroundTime / totalItems;
            System.out.println("Average Turnaround Time: " + avgTurnaroundTime);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args)
    {
        File file = new File("PRODUCER_CONSUMER/Input-4sec-Wait.txt");
        List<TestCase> testCases = FileReader.readFile(file.getAbsolutePath());

        for(TestCase testCase : testCases)
        {
            runThreads(testCase.getSleepTime(), testCase.getNumProducers(), testCase.getNumConsumers());
        }

    }


}


