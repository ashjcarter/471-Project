package CPUSCHED;

import java.util.Queue;

public class Fifo 
{
    public static void calcTurnaround(Queue<Process> processQueue)
    {
        int totalTurnaround = 0;
        int numProcess = processQueue.size();

        for(Process process: processQueue)
        {
            int endTime = process.getArrivalTime() + process.getBurstUnits();
            System.out.println("End time: " + endTime);
            int turnaround = endTime - process.getArrivalTime();
            process.setTurnaroundtime(turnaround);
            totalTurnaround += turnaround;
        }
        double avgTurnaround = (double)totalTurnaround / numProcess;
        System.out.println("Average Turnaround Time: " + avgTurnaround);
    }

    public static void calcWait(Queue<Process> processQueue)
    {
        int waitTimeTotal = 0;
        int currTime = 0;
        int numProcess = processQueue.size();

        for(Process process: processQueue)
        {
           
            int waitTime = (process.getTurnaroundtime() - process.getBurstUnits());
            System.out.println("Wait time: " + waitTime);
            process.setWaitTime(waitTime);
            waitTimeTotal += waitTime;
            currTime += process.getBurstUnits();
            System.out.println("Current time: " + currTime);
            
        }
        double avgWaitTime = (double)waitTimeTotal / numProcess;
        System.out.println("Average Wait Time: " + avgWaitTime);
    }

    public static void elaspedTime(Queue<Process> processQueue)
    {
        int throughput = processQueue.size()/ 10;
        System.out.println("Throughput: " + processQueue.size());

    }
}
