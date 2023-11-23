package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * The Main class that simulates the producer-consumer problem using Java threads.
 * It creates producers and consumers according to the test cases read from a file. 
 */
public class Main
{
    private static final int bufferSize = 5;
    private static final BlockingQueue<Item> buffer = new LinkedBlockingQueue<>(bufferSize);

    // Flag to control the exectuion of producer and consumer threads
    private static final AtomicBoolean runThreads = new AtomicBoolean(true);

    private static int sleepTime = 1; // Default sleep time override within runThreads()
    private static double totalTurnaroundTime = 0;
    private static int totalItems = 0;

    /**
     * Producer logic: Generates items and inserts them into the buffer.
     */
    private static void producer()
    {
        Random rand = new Random();
        // Keep producting items and insert into buffer until falg is set to false
        while(runThreads.get()) 
        {
            try
            {
                Thread.sleep(sleepTime);
                // Generate random item to be stored into the buffer
                int randItem = rand.nextInt(100);
                // Creating an item with the random value
                Item item = new Item(randItem);
                buffer.put(item);

            }
            catch(InterruptedException e)
            {
                // System.out.println("Producer interrupted.");
                break;
            }
        }
    }

    /**
     * Consumer logic: Consumes items from the buffer and calculates their turnaround time.
     */
    private static void consumer()
    {
        // Keep consuming items until the flag is set to false or the buffer is empty
        while(runThreads.get() || !buffer.isEmpty()) 
        {
            try
            {
                Item item = buffer.take();
                double turnaroundTime = System.currentTimeMillis() - item.getProductionTime();
                totalTurnaroundTime += turnaroundTime;
                totalItems++;
                Thread.sleep(sleepTime);
            }
            catch(InterruptedException e)
            {
                System.out.println("Consumer interrupted.");
                break;
            }

        }
    }

    /**
     * Runs the producer and consumer threads based on the test case configuration.
     *
     * @param testCase The test case defining the simulation parameters.
     */
    public static void runThreads(TestCase testCase)
    {
        List<Thread> threads = new ArrayList<>(); // List to hold the producer and consumer threads
        int numProducers = testCase.getNumProducers();
        int numConsumers = testCase.getNumConsumers();
        int runThreadsSleep = sleepTime * 100; // Sleep time for the simulation 
    
        sleepTime = testCase.getSleepTime(); // Sleep time for producer and consumer threads
        runThreads.set(true); // Flag to control the exectuion of producer and consumer threads

        // Create and start the producer thread
        for(int i = 0; i < numProducers; i++)
        {
            Thread producerThread = new Thread(Main::producer);
            producerThread.start();
            threads.add(producerThread);
        }

        // Create and start the consumer thread 
        for(int j = 0; j < numConsumers; j++)
        {
            Thread consumerThread = new Thread(Main::consumer);
            consumerThread.start();
            threads.add(consumerThread);
        }

        try
        {
            Thread.sleep(runThreadsSleep);
            runThreads.set(false); // Set flag to false to stop producer and consumer threads

            // Interrupt all threads
            for(Thread thread : threads)
            {
                thread.interrupt();
            }

            // Wait for all threads to finish
            for(Thread thread : threads)
            {
                thread.join();
            }

            double avgTurnaroundTime = totalTurnaroundTime / (double)totalItems ;

            WriteFile.writeFile(testCase.getOutFile(), testCase.getTestCaseID(), 
                                sleepTime, numProducers, numConsumers, avgTurnaroundTime);
        }
        catch(InterruptedException e)
        {
            Thread.currentThread().interrupt();
        }
        
    }

    /**
     * Main method: Reads input files and runs simulations.
     *
     * @param args Command-line arguments(unused in this case).
     */
    public static void main(String[] args)
    {
        List<String> files = Menu.selectFile();

        // Getting input and output file names from the list
        String inFile = files.get(0);
        String outFile = files.get(1);

        System.out.println("You've chosen " + inFile);
        System.out.println("Running simulation...");

        // Calling the readFile method to read the input file and create a list of test cases
        List<TestCase> testCases = ReadFile.readFile(inFile);

        // Looping through each test case, running the simulation 
        //and writing the output to the output file
        for(TestCase testCase : testCases)
        {
            testCase.setOutFile(outFile);
            runThreads(testCase);
        }
        System.out.println("Simulation completed. Check the output file" + outFile + ".");

    }

}


