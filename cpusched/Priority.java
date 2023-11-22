package cpusched;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class Priority extends Calculations
{
    public String priorityScheduling(Queue<Process> processQueue)
    {
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        Set<Process> finishedProcesses = new HashSet<>();

        Process currProcess = null;
        boolean isFirstTime = true;
        
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalProcessingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int currTime = 0;

        while(!processQueue.isEmpty() ||!readyQueue.isEmpty() || currProcess != null)
        {
            while(!processQueue.isEmpty() && processQueue.peek().getArrivalTime() <= currTime)
            {
                readyQueue.add(processQueue.poll());
            }

            if(currProcess == null || (!readyQueue.isEmpty() && readyQueue.peek().getPriority() 
                                        < currProcess.getPriority() && readyQueue.peek().getArrivalTime() 
                                        <= currProcess.getArrivalTime()))
            {
    
                if(currProcess != null && currProcess.getBurstUnits() > 0)
                {
                    readyQueue.add(currProcess);
                }
                currProcess = readyQueue.poll();
                isFirstTime = true; // Resetting for when a new process starts
                
            }

            if(currProcess !=null && currProcess.getArrivalTime() <= currTime)
            {

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
                currProcess.decrementBurst();

                totalProcessingTime++;
                if(currProcess.getBurstUnits() == 0)
                {
                    totalTurnaroundTime += currTime - currProcess.getArrivalTime();
                    currProcess = null;
                }

            }
            else
            {
                currTime++;
            }

        }
        Calculations calc = new Calculations();
        return calc.generateStats(currTime, numProcess, totalWaitTime, 
                    totalTurnaroundTime, totalResponseTime, totalProcessingTime);




    }
}
