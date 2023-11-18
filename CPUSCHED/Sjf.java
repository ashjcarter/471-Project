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

        PriorityQueue<Process> readyQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getArrivalTime));
        int numProcess = processQueue.size();
        int totalWaitTime = 0;
        int totalTurnaroundTime = 0;
        int totalResponseTime = 0;
        int currTime = 0;
        int index = 0;

        while(!readyQueue.isEmpty() || index < numProcess)
        {
            while(index < numProcess && processes.get(index).getArrivalTime() <= currTime)
            {
                readyQueue.offer(processes.get(index++));
            }
        }


        for( Process process : processes)
        {
            currTime = Math.max(currTime, process.getArrivalTime());
            // System.out.println("Current time: " + currTime);
            // System.out.println("I'm in for loop");

            int waitTime = currTime - process.getArrivalTime();
            totalWaitTime += waitTime;

            currTime += process.getBurstUnits();

            int turnAroundTime = waitTime + process.getBurstUnits();
            totalTurnaroundTime += turnAroundTime;
            System.out.println("Turnaround times: " + turnAroundTime);

        }


        //     }
            
        // }
   
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
