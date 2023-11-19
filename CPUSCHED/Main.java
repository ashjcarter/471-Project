package CPUSCHED;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main 
{
    private static Queue<Process> readFile(String fileName)
    {
        Queue<Process> processQueue = new LinkedList<>();
        int processID = 0;

        try
        {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }

            String line = " ";
            int count = 0;
           
            while(scanner.hasNextLine() && count < 10)
            {
                line = scanner.nextLine();
                
                String[] processFile = line.trim().split("\t");
                  
                int arrivalTime = Integer.parseInt(processFile[0]);
                int burstUnits = Integer.parseInt(processFile[1]);
                int priority = Integer.parseInt(processFile[2]);

                Process processes = new Process(processID, arrivalTime, burstUnits, priority);
                processQueue.add(processes);
                processID++;
                count++;

            }
            scanner.close();

        } catch(FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return processQueue;
 
    }

    public static void main (String[] args)
    {
        Queue<Process> processQueue = readFile("CPUSCHED/Datafile1-txt.txt");

        System.out.println("\nStatistics for the Run\n");
        System.out.println("Number of processess: " + processQueue.size());
        
        // Fifo fifo= new Fifo();
        // fifo.fifoScheduling(processQueue);

        // Sjf sjf = new Sjf();
        // sjf.sjfScheduling(processQueue);
        
        Priority priority = new Priority();
        priority.priorityScheduling(processQueue);

        

    }
    
}
