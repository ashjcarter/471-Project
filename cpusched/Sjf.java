package cpusched;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class implements the Shortest Job First (SJF) Scheduling algorithm.
 * Extends the Calculations class to generate statistics based on the SJF Scheduling.
 */
public class Sjf extends Calculations
{
    /**
     * Executes the Shortest Job First (SJF) Scheduling algorithm on the provided process queue
     * and generates statistics based on the scheduling.
     *
     * @param processQueue The queue of processes to be scheduled using SJF.
     * @return A string containing the calculated statistics.
     */
    public String sjfScheduling(Queue<Process> processQueue)
    {
        // Convert the process queue to a list for sorting by arrival time
        List<Process> processList = new ArrayList<>(processQueue);
        Collections.sort(processList, Comparator.comparingInt(Process::getArrivalTime));

        // Priority queue to store processes in order of burst time
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstUnits));
        
        // Initialize variables to store statistics
        int numProcess = processQueue.size();
        int turnaroundTime = 0;
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalProcessingTime = 0;
        int waitTime = 0;
        int currTime = 0;
        int index = 0;

        // Process the ready queue until all processes are executed
        while(!readyQueue.isEmpty() || index < numProcess)
        {
            // Add processes to ther ready queue that have arrived at the current time
            while(index < numProcess && processList.get(index).getArrivalTime() <= currTime)
            {
                readyQueue.offer(processList.get(index++));
            }
        
        // If the ready queue is not empty
        if(!readyQueue.isEmpty())
        {
            // Process the shortest job in the ready queue
            Process currProcess = readyQueue.poll();

            // If the process has arrived and is ready to execute
            if(currProcess.getArrivalTime() <= currTime)
            {
                // Calculate the statistics
                waitTime = currTime - currProcess.getArrivalTime();
                currTime += currProcess.getBurstUnits();
                totalWaitTime += waitTime;
                totalResponseTime += waitTime;
                totalProcessingTime += currProcess.getBurstUnits();
                totalTurnaroundTime += waitTime + currProcess.getBurstUnits();
            }
            else
            {
                // Else the process arrives after the current time, 
                // So start its execution when it arrives
                currTime = currProcess.getArrivalTime() + currProcess.getBurstUnits();
            }
        }
        else
        {
            // Else no processes are ready, move time forward 
            // To the next process arrival time
            currTime = processList.get(index).getArrivalTime();
        }
    }
    
    // Create an instance of the Calculations class to generate and return statistics
    Calculations calc = new Calculations();
    return calc.generateStats(currTime, numProcess, totalWaitTime, 
                              totalTurnaroundTime, totalResponseTime, totalProcessingTime);
        
    }
    
}
