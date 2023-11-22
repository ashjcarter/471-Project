package cpusched;

import java.util.Queue;

/**
 * This class represents a First-In-First-Out (FIFO) scheduling algorithm.
 * Extends the Calculations class to generate statistics based on FIFO scheduling.
 */
public class Fifo extends Calculations 
{    
    /**
    * Performs FIFO scheduling on the given process queue and generates statistics.
    *
    * @param processQueue   The queue containing processes to be scheduled.
    * @return               A string containing the statistics generated from FIFO scheduling.
    */
    public String fifoScheduling(Queue<Process> processQueue)
    {
        // Initialize variables to store statistics 
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalProcessingTime = 0;
        int currTime = 0;

        // Process each item in the queue using FIFO
        while(!processQueue.isEmpty())
        {
            Process currProcess = processQueue.poll();

            // Update current time if the current process has arrived later
            if(currProcess.getArrivalTime() > currTime)
            {
                currTime = currProcess.getArrivalTime();
            }

            // Calculate wait time for the current process
            int waitTime = currTime - currProcess.getArrivalTime();

            // Update statistics
            totalWaitTime += waitTime;
            currTime += currProcess.getBurstUnits();
            totalProcessingTime += currProcess.getBurstUnits();  
            totalTurnaroundTime += currTime - currProcess.getArrivalTime();
            totalResponseTime += waitTime;
        }

        // Create an instance of the Calculations class to generate and return statistics
        Calculations calc = new Calculations();
        return calc.generateStats(currTime, numProcess, totalWaitTime, 
                                  totalTurnaroundTime, totalResponseTime, totalProcessingTime);
    }
}
