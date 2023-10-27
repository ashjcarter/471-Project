package CPUSCHED;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main 
{
    private static Queue<ProcessData> readFile(String fileName)
    {
        Queue<ProcessData> processQueue = new LinkedList<>();

        try
        {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }

            while(scanner.hasNextLine())
            {
                String line = scanner.nextLine();
                String[] processFile = line.trim().split("\t");
        
                int processID = 0;
                int arrivalTime = Integer.parseInt(processFile[0]);
                int burstUnits = Integer.parseInt(processFile[1]);
                int priority = Integer.parseInt(processFile[2]);

                ProcessData processes = new ProcessData(processID, arrivalTime, burstUnits, priority);
                processQueue.add(processes);

                processID++;

            }
            scanner.close();
        }catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return processQueue;
    }

    public static void main (String[] args)
    {
        Queue<ProcessData> processQueue = readFile("CPUSCHED/Datafile1.txt");

        for(ProcessData processes : processQueue)
        {
            System.out.println("Process ID: " + processes.getProcessID());
            System.out.println("Arrival Time: " + processes.getArrivalTime());
            System.out.println("Priority: " + processes.getPriority());
            System.out.println("Burst Units: " + processes.getBurstUnits());
            System.out.println("Start Time: " + processes.getStartTime());
            System.out.println("End Time: " + processes.getEndTime());
            System.out.println(" ");
        }

    }
    
}
