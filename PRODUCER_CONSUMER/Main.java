package PRODUCER_CONSUMER;

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
    private static int sleepTime = 0;
    private static long totalTurnaroundTime = 0;
    private static int totalItems = 0;

    private static void producer()
    {
        // Logic to produce items and insert into buffer
        while(runThreads.get()) 
        {
            try
            {
                Thread.sleep(rand.nextInt(sleepTime) * 1000);
                int value = rand.nextInt(10);
                Item item = new Item(value);
                buffer.put(item);

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
                double turnaroundTime = System.currentTimeMillis() - item.getProductionTime();
                totalTurnaroundTime += turnaroundTime;
                totalItems++;
            }
            catch(InterruptedException e)
            {
                break;
            }

        }
    }

    public static void runThreads(TestCase testCase)
    {
        List<Thread> threads = new ArrayList<>();
        int numProducers = testCase.getNumProducers();
        int numConsumers = testCase.getNumConsumers();
    
        sleepTime = testCase.getSleepTime();
        runThreads.set(true);

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

            WriteFile.writeFile(testCase.getOutFile(), testCase.getTestCaseID(), 
            sleepTime, numProducers, numConsumers, avgTurnaroundTime);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void main(String[] args)
    {
        List<String> files = Menu.selectFile();
        String inFile = files.get(0);
        String outFile = files.get(1);

        System.out.println("You've chosen " + inFile);
        System.out.println("Running simulation...");

        List<TestCase> testCases = ReadFile.readFile(inFile);
        for(TestCase testCase : testCases)
        {
            testCase.setOutFile(outFile);
            runThreads(testCase);
        }
        System.out.println("Simulation completed. Check the output file.");

    }

}


