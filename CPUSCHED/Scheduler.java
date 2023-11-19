package CPUSCHED;

import java.text.DecimalFormat;

public abstract class Scheduler 
{
    protected void printCalc(int currTime, int numProcess, int totalWaitTime, 
    int totalTurnaroundTime, int totalResponseTime, int totalProcessingTime)
    {
        double throughput = (double)numProcess / currTime;
        double cpuUtilization = ((double)totalProcessingTime / (currTime)) * 100;
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Total elaspted time: " + df.format(currTime));
        System.out.println("Throughput: " + df.format(throughput));
        System.out.println("CPU utilization: "  + df.format(cpuUtilization) + "%");
        System.out.println("Average wait time (in CPU burst times): " + df.format(avgWaitTime));
        System.out.println("Average turnaround time (in CPU burst times): " + df.format(avgTurnaroundTime));
        System.out.println("Average response time (in CPU burst times): " + df.format(avgResponseTime));
    }
    
}
