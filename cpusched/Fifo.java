package cpusched;

import java.util.Queue;

public class Fifo extends Calculations 
{
    public String fifoScheduling(Queue<Process> processQueue)
    {
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int totalProcessingTime = 0;
        int currTime = 0;

        while(!processQueue.isEmpty())
        {
            Process currProcess = processQueue.poll();

            if(currProcess.getArrivalTime() > currTime)
            {
                currTime = currProcess.getArrivalTime();
            }

            int waitTime = currTime - currProcess.getArrivalTime();

            totalWaitTime += waitTime;
            currTime += currProcess.getBurstUnits();
            totalProcessingTime += currProcess.getBurstUnits();
    
            totalTurnaroundTime += currTime - currProcess.getArrivalTime();
            totalResponseTime += waitTime;
        }

        Calculations calc = new Calculations();
        return calc.generateStats(currTime, numProcess, totalWaitTime, 
                    totalTurnaroundTime, totalResponseTime, totalProcessingTime);
    }
}
