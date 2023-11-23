package producer_consumer;

import java.io.IOException;
import java.io.FileWriter;

/**
 * This class provides a utility method for writing data to a file.
 */
public class WriteFile 
{
    /**
     * This method writes the specified data to the output file. If it's the first test case,
     * it creates a new file and writes the headers. For subsequent test cases, 
     * it appends the data to the existing file.
     * 
     * @param outFile           The name of the output file.
     * @param testCaseID        The test case ID.
     * @param sleepTime         The sleep time.
     * @param numProducers      The number of producers.
     * @param numConsumers      The number of consumers.
     * @param avgTurnaroundTime The average turnaround time.
     */
    public static void writeFile(String outFile, int testCaseID, int sleepTime, 
                                int numProducers, int numConsumers, double avgTurnaroundTime)
    {
        FileWriter writer = null;
        try
        {
            // If it's the first test case, create a new file and write the headers
            if(testCaseID == 1)
            {
                writer = new FileWriter(outFile);
                writer.write(String.format("%-10s %-15s %-20s %-20s %-20s%n",
                "Test Case", "Sleep Time", "Number of Producers", 
                "Number of Consumers", "Average Turnaround Time(ms)" ));

            }
            else
            {
                // Else append the data to the existing file
                writer = new FileWriter(outFile, true);
            }
            // Write the data to the file
            writer.write(String.format("%-10d %-15d %-20d %-20d %-20.2f%n", testCaseID,
                                      sleepTime, numProducers, numConsumers, avgTurnaroundTime));
            // Flush the writer to ensure the data is written to the file
            writer.flush();
        }
        catch(IOException e)
        {
            System.out.println("An error occurred while writing to the output file.");
            e.printStackTrace();
        }
        finally
        {
            if(writer != null)
            {
                try
                {
                    writer.close();
                }
                catch(IOException e)
                {
                    System.out.println("An error occurred while closing the output file.");
                    e.printStackTrace();
                }
            }
        }
    }
    
}
