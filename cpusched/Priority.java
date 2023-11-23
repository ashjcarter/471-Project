package cpusched;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * This class implements a Priority Scheduling algorithm.
 * It extends the Calculations class to generate statistics 
 * based on the Priority Scheduling.
 */
public class Priority extends Calculations
{
    /**
     * Executes the Priority Scheduling algorithm on the provided process queue and generates statistics.
     *
     * @param processQueue  The queue of processes to be scheduled.
     * @return              A string containing the calculated statistics.
     */
    public String priorityScheduling(Queue<Process> processQueue)
    {
        // Priority queue to store processes in order of priority
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        
        // Set to keep track of finished processes
        Set<Process> finishedProcesses = new HashSet<>();

        Process currProcess = null; // Current process executing
        boolean isFirstTime = true; // Flag to indiciate if it's the first time a process is exectuing 
        
        // Variables to keep track of statistics
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalProcessingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int currTime = 0;

        // Main loop until all processes are processed
        while(!processQueue.isEmpty() ||!readyQueue.isEmpty() || currProcess != null)
        {
            // Add processes that have arrived to the ready queue
            while(!processQueue.isEmpty() && processQueue.peek().getArrivalTime() <= currTime)
            {
                readyQueue.add(processQueue.poll());
            }

            // If a new process with higher priority arrives or the current process finishes
            if(currProcess == null || (!readyQueue.isEmpty() && readyQueue.peek().getPriority() 
                                        < currProcess.getPriority() && readyQueue.peek().getArrivalTime() 
                                        <= currProcess.getArrivalTime()))
            {
                // Adds the current process to the ready queue if it has burst time remaining
                if(currProcess != null && currProcess.getBurstUnits() > 0)
                {
                    readyQueue.add(currProcess);
                }
                currProcess = readyQueue.poll(); // Get the process with the higher priority  
                isFirstTime = true; // Resetting for when a new process starts
                
            }

            // Execute the current process
            if(currProcess !=null && currProcess.getArrivalTime() <= currTime)
            {
                // Calculates wait and response time for the first execution of a process
                if(isFirstTime)
                {
                    totalWaitTime += currTime - currProcess.getArrivalTime();
                    if(!finishedProcesses.contains(currProcess))
                    {
                        totalResponseTime += currTime - currProcess.getArrivalTime();
                        finishedProcesses.add(currProcess);
                    }
                    isFirstTime = false;
                }

                currTime++;
                currProcess.decrementBurst(); // Decrement the burst time for the current process
                totalProcessingTime++;

                // Checks if the current process has finished
                if(currProcess.getBurstUnits() == 0)
                {
                    totalTurnaroundTime += currTime - currProcess.getArrivalTime();
                    currProcess = null; // Switching to the next process 
                }

            }
            else
            {
                currTime++;
            }

        }
        
        // Create an instance of the Calculations class to generate and return statistics
        Calculations calc = new Calculations();
        return calc.generateStats(currTime, numProcess, totalWaitTime, 
                                  totalTurnaroundTime, totalResponseTime, totalProcessingTime);

    }
}
