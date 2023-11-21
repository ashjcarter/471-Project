package PRODUCER_CONSUMER;

import java.io.IOException;
import java.io.FileWriter;

public class WriteFile 
{
    public static void writeFile(String outFile, int testCaseID, int sleepTime, int numProducers, 
    int numConsumers, double avgTurnaroundTime)
    {
        FileWriter writer = null;
        try
        {
            if(testCaseID == 1)
            {
                writer = new FileWriter(outFile);
                writer.write(String.format("%-10s %-15s %-20s %-20s %-20s%n",
                "Test Case", "Sleep Time", "Number of Producers", 
                "Number of Consumers", "Average Turnaround Time(ms)" ));

            }
            else
            {
                writer = new FileWriter(outFile, true);
            }
            writer.write(String.format("%-10d %-15d %-20d %-20d %-20.2f%n", testCaseID,
            sleepTime, numProducers, numConsumers, avgTurnaroundTime));
            writer.flush();
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while writing to the output file.");
            e.printStackTrace();
        }
    }
    
}
