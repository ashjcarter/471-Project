package CPUSCHED;

import java.util.Queue;

public class Fifo extends Scheduler 
{
    public void fifoScheduling(Queue<Process> processQueue)
    {
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalProcessingTime = 0;
        int startTime = -1;
        int currTime = 0;
        // System.out.println("(Fifo) Number of processess: " + numProcess);
        while(!processQueue.isEmpty())
        {
            Process currProcess = processQueue.poll();

            if(currProcess.getArrivalTime() > currTime)
            {
                currTime = currProcess.getArrivalTime();
            }

            if(startTime == -1)
            {
                startTime = currProcess.getArrivalTime();
            }

            int waitTime = currTime - currProcess.getArrivalTime();
            // System.out.println("Wait time: " + waitTime);
            totalWaitTime += waitTime;
            currTime += currProcess.getBurstUnits();
            totalProcessingTime += currProcess.getBurstUnits();
    
            totalTurnaroundTime += currTime - currProcess.getArrivalTime();
            totalResponseTime += waitTime;
        }

        printCalc(startTime, currTime, numProcess, totalWaitTime, 
                    totalTurnaroundTime, totalResponseTime, totalProcessingTime);
    }
}
