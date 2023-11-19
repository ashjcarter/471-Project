package CPUSCHED;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class Sjf 
{
    
    public void sjfScheduling(Queue<Process> processQueue)
    {
        // System.out.println("I'm in SJF.java");
        List<Process> processes = new ArrayList<>(processQueue);
        Collections.sort(processes, Comparator.comparingInt(Process::getArrivalTime));
        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstUnits));
        
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        double totalThroughput = 0;
        int totalTurnaroundTime = 0;
        double totalResponseTime = 0;
        double totalProcessingTime = 0;
        double startTime = processes.get(0).getArrivalTime();
        double endTime = 0;
        int waitTime = 0;
        int currTime = 0;
        int index = 0;
        int count = 0;

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
            double responseTime = (double)(currTime - currProcess.getArrivalTime()) / currProcess.getBurstUnits();
            totalResponseTime += responseTime;
            count++;

            totalThroughput += 1.0 / currProcess.getBurstUnits();

            totalProcessingTime += currProcess.getBurstUnits();
            endTime = currTime;

            totalWaitTime += waitTime;

            int turnAroundTime = waitTime + currProcess.getBurstUnits();
            totalTurnaroundTime += turnAroundTime;

        }
        else
        {
            currTime = processes.get(index).getArrivalTime();
        }
    }

        double cpuUtilization = (totalProcessingTime / (endTime - startTime)) * 100;
        double avgWaitTime = (double)totalWaitTime / numProcess;
        double avgTurnaroundTime = (double)totalTurnaroundTime / numProcess;
        double avgResponseTime = (double)totalResponseTime / numProcess;
        DecimalFormat df = new DecimalFormat("#.##");

        System.out.println("Total elaspted time: " + df.format(currTime));
        System.out.println("Throughput: " + df.format(totalThroughput));
        System.out.println("CPU utilization:" + df.format(cpuUtilization) + "%");
        System.out.println("Average wait time (in CPU burst times): " + df.format(avgWaitTime));
        System.out.println("Average turnaround time (in CPU burst times): " + df.format(avgTurnaroundTime));
        System.out.println("Average response time (in CPU burst times): " + df.format(avgResponseTime));
       

        
    }
    
}
