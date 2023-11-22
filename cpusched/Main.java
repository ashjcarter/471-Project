package cpusched;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
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
           
            while(scanner.hasNextLine() && count < 11)
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
        Queue<Process> processQueue = readFile("cpusched/Datafile1-txt.txt");
        Menu menu = new Menu();
        int choice = menu.display();
        String outFile = "";
        String output = "";

        switch(choice)
        {
            case 1:
                Fifo fifo= new Fifo();
                output = fifo.fifoScheduling(processQueue);
                outFile = "cpusched/Fifo-Output.txt";
                break;
            case 2:
                Sjf sjf = new Sjf();
                output = sjf.sjfScheduling(processQueue);
                outFile = "cpusched/Sjf-Output.txt";
                break;
            case 3:
                Priority priority = new Priority();
                output = priority.priorityScheduling(processQueue);
                outFile = "cpusched/Priority-Output.txt";
                break;
            default:
                System.out.println("Invalid input. Exiting...");
                Fifo fifo2 = new Fifo();
                output = fifo2.fifoScheduling(processQueue);
                outFile = "cpusched/Fifo-Output.txt";
        }
        try(PrintWriter out = new PrintWriter(outFile))
        {
            out.println(output);
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        }
        
    }
    
}
