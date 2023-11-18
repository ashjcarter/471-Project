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
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
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

            totalWaitTime += waitTime;

            int turnAroundTime = waitTime + currProcess.getBurstUnits();
            totalTurnaroundTime += turnAroundTime;

            totalResponseTime += waitTime;

        }
        else
        {
            currTime = processes.get(index).getArrivalTime();
        }
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
