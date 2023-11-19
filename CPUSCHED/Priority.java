package CPUSCHED;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class Priority extends Scheduler
{
    public void priorityScheduling(Queue<Process> processQueue)
    {
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        Process currProcess = null;
        boolean firstTime = true;
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalProcessingTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int startTime = -1;
        int waitTime = 0;
        int currTime = 0;

        while(!processQueue.isEmpty() ||!readyQueue.isEmpty() || currProcess != null)
        {
            // System.out.println("I'm in outer while loop");

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
                firstTime = true; // Resetting for when a new process starts
                
            }

            if(currProcess !=null && currProcess.getArrivalTime() <= currTime)
            {
                if(startTime == -1)
                {
                    startTime = currTime;
                }
                if(firstTime)
                {
                    totalWaitTime += currTime - currProcess.getArrivalTime();
                    firstTime = false;
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

            totalResponseTime = totalWaitTime;


        }
        printCalc(startTime, currTime, numProcess, totalWaitTime, 
                    totalTurnaroundTime, totalResponseTime, totalProcessingTime);




    }
}
