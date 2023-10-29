package CPUSCHED;

import java.util.Queue;

public class Fifo 
{
    public static void calcTurnaround(Queue<ProcessData> processQueue)
    {
        int totalTurnaround = 0;
        int numProcess = processQueue.size();

        for(ProcessData process: processQueue)
        {
            int endTime = process.getArrivalTime() + process.getBurstUnits();
            int turnaround = endTime - process.getArrivalTime();
            totalTurnaround += turnaround;
        }
        double avgTurnaround = (double)totalTurnaround / numProcess;
        System.out.println("Average Turnaround Time: " + avgTurnaround);
    }
    public static void calcWait(Queue<ProcessData> processQueue)
    {
        int waitTimeTotal = 0;
        int currTime = 10;
        int numProcess = processQueue.size();
        for(ProcessData process: processQueue)
        {
            int waitTime = Math.max(0, currTime - process.getArrivalTime());
            waitTimeTotal += waitTime;
            currTime += process.getBurstUnits();
        }
        double avgWaitTime = (double)waitTimeTotal / numProcess;
        System.out.println("Total Wait Time: " + waitTimeTotal);
        System.out.println("Average Wait Time: " + avgWaitTime);
    }
}
