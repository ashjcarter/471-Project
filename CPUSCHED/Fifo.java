package CPUSCHED;

import java.text.DecimalFormat;
import java.util.Queue;

public class Fifo 
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
        
        double throughput = (double)numProcess / currTime;
        double cpuUtilization = ((double)totalProcessingTime / (currTime - startTime)) * 100;
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Total elaspted time: " + df.format(currTime));
        System.out.println("Throughput: " + df.format(throughput));
        System.out.println("CPU utilization:"  + df.format(cpuUtilization) + "%");
        System.out.println("Average wait time (in CPU burst times): " + df.format(avgWaitTime));
        System.out.println("Average turnaround time (in CPU burst times): " + df.format(avgTurnaroundTime));
        System.out.println("Average response time (in CPU burst times): " + df.format(avgResponseTime));
        
    }
}
