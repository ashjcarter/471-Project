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
        int startTime = processes.get(0).getArrivalTime();
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
            // System.out.println("Finish time for process " + currProcess.getProcessID() + ": " + currTime);
            // System.out.println("Burst time for process " + currProcess.getProcessID() + ": " + currProcess.getBurstUnits());
            // System.out.println("Turnaround time for process " + currProcess.getProcessID() + ": " + (currTime - currProcess.getArrivalTime()));
            // System.out.println("Waiting time for process " + currProcess.getProcessID() + ": " + waitTime);
            // System.out.println("Arrival Times: " + currProcess.getArrivalTime());
            // double responseTime = (double)(currTime - currProcess.getArrivalTime()) / currProcess.getBurstUnits();
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

    printCalc(startTime, currTime, numProcess, totalWaitTime, 
               totalTurnaroundTime, totalResponseTime, totalProcessingTime);

        // double throughput = (double)numProcess/currTime;
        // double cpuUtilization = (totalProcessingTime / (endTime - startTime)) * 100;
        // double avgWaitTime = (double)totalWaitTime / numProcess;
        // double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        // double avgResponseTime = (double)totalResponseTime / numProcess;
        // DecimalFormat df = new DecimalFormat("#.##");

        // System.out.println("Total elaspted time: " + df.format(currTime));
        // System.out.println("Throughput: " + df.format(throughput));
        // System.out.println("CPU utilization:" + df.format(cpuUtilization) + "%");
        // System.out.println("Average wait time (in CPU burst times): " + df.format(avgWaitTime));
        // System.out.println("Average turnaround time (in CPU burst times): " + df.format(avgTurnaroundTime));
        // System.out.println("Average response time (in CPU burst times): " + df.format(avgResponseTime));
        
    }
    
}
