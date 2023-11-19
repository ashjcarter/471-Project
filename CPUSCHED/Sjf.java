package CPUSCHED;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sjf extends Scheduler
{
    
    public void sjfScheduling(Queue<Process> processQueue)
    {
        // System.out.println("I'm in SJF.java");
        List<Process> processes = new ArrayList<>(processQueue);
        Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstUnits));
        
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalProcessingTime = 0;
        int waitTime = 0;
        int currTime = 0;
        int index = 0;

        // System.out.println("Before outer while loop");
        while(!readyQueue.isEmpty() || index < numProcess)
        {
            // System.out.println("Inside outer while loop");
            while(index < numProcess && processes.get(index).getArrivalTime() <= currTime)
            {
                // System.out.println("Inside inner while loop");
                readyQueue.offer(processes.get(index++));
            }
        

        if(!readyQueue.isEmpty())
        {
            
            Process currProcess = readyQueue.poll();
            if(currProcess.getArrivalTime() <= currTime)
            {
                waitTime = currTime - currProcess.getArrivalTime();
                currTime += currProcess.getBurstUnits();
            }
            else
            {
                currTime = currProcess.getArrivalTime() + currProcess.getBurstUnits();
            }

            totalResponseTime += waitTime;

            totalProcessingTime += currProcess.getBurstUnits();

            totalWaitTime += waitTime;

            int turnAroundTime = waitTime + currProcess.getBurstUnits();
            totalTurnaroundTime += turnAroundTime;

        }
        else
        {
            currTime = processes.get(index).getArrivalTime();
        }
    }

    printCalc(currTime, numProcess, totalWaitTime, 
               totalTurnaroundTime, totalResponseTime, totalProcessingTime);
        
    }
    
}
