package PRODUCER_CONSUMER;

import java.io.File;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main
{
    // private static final Buffer buffer = new Buffer();
    private static final BlockingQueue<Item> buffer = new LinkedBlockingQueue<>();
    private static final AtomicBoolean runThreads = new AtomicBoolean(true);
    private static final Random rand = new Random();
    private static int sleepTime;

    private static void producer()
    {
        // Logic to produce items and insert into buffer
        while(runThreads.get()) 
        {
            try
            {
                System.out.println("Sleeping for " + sleepTime);
                Thread.sleep(rand.nextInt(sleepTime * 1000));
                int value = rand.nextInt(10);
                Item item = new Item(value);
                buffer.put(item);

                System.out.println("Producer produced " + item.getValue());
            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }
        }
    }

    private static void consumer()
    {
        // Logic to consume items and remove from buffer
        while(runThreads.get()) 
        {
            try
            {
                Item item = buffer.take();
                long turnaroundTime = System.currentTimeMillis() - item.getProductionTime();
                System.out.println("Consumed item: " + item.getValue() + 
                " Turnaround time: " + turnaroundTime);

            }
            catch(InterruptedException e)
            {
                Thread.currentThread().interrupt();
            }

        }
    }

    public static void runThreads(int sleepTime, int numProducers, int numConsumers)
    {
        Main.sleepTime = sleepTime;

        runThreads.set(true);
        for(int i = 0; i < numProducers; i++)
        {
            Thread producerThread = new Thread(Main::producer);
            producerThread.start();
        }

        for(int j = 0; j < numConsumers; j++)
        {
            Thread consumerThread = new Thread(Main::consumer);
            consumerThread.start();
        }

        try
        {
            Thread.sleep(sleepTime * 1000);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        runThreads.set(false);
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


