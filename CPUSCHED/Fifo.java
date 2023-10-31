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
        int currTime = 0;
        // System.out.println("(Fifo) Number of processess: " + numProcess);
        while(!processQueue.isEmpty())
        {
            Process currProcess = processQueue.poll();

            if(currProcess.getArrivalTime() > currTime)
            {
                currTime = currProcess.getArrivalTime();
            }

            int waitTime = currTime - currProcess.getArrivalTime();
            // System.out.println("Wait time: " + waitTime);
            totalWaitTime += waitTime;
            currTime += currProcess.getBurstUnits();
    
            totalTurnaroundTime += currTime - currProcess.getArrivalTime();
            totalResponseTime += waitTime;
        }
        
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        double throughput = (double)numProcess / currTime;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Elaspted time: " + df.format(currTime));
        System.out.println("Throughput: " + df.format(throughput));
        System.out.println("CPU Utilization: " + df.format(throughput * 100));
        System.out.println("Average Wait Time (in CPU burst times): " + df.format(avgWaitTime));
        System.out.println("Average Turnaround Time (in CPU burst times): " + df.format(avgTurnaroundTime));
        System.out.println("Average Response Time (in CPU burst times): " + df.format(avgResponseTime));
        
    }
}
