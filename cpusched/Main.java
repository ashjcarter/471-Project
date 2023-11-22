package cpusched;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * This class contains the main entry point and methods for reading a file, scheduling processes,
 * and writing the output to a file based on different scheduling algorithms.
 */
public class Main 
{
    /**
     * Reads a file and creates a queue of processes based on the file content.
     *
     * @param fileName   The name of the file to read containing process information.
     * @return           A queue of Process objects.
     */
    private static Queue<Process> readFile(String fileName)
    {
        // Creating a queue to store the processes
        Queue<Process> processQueue = new LinkedList<>();
        int processID = 0;
        int count = 0;
        String line = " ";

        try
        {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            // Skip the header line in the file
            if(scanner.hasNextLine())
            {
                scanner.nextLine();
            }
            
            // Read the first 500 lines of the file
            while(scanner.hasNextLine() && count < 500)
            {
                line = scanner.nextLine();
                
                String[] processFile = line.trim().split("\t");
                
                // Extract the process information from the file 
                int arrivalTime = Integer.parseInt(processFile[0]);
                int burstUnits = Integer.parseInt(processFile[1]);
                int priority = Integer.parseInt(processFile[2]);

                // Create a new Process object with the extracted values and current process ID
                Process processes = new Process(processID, arrivalTime, burstUnits, priority);
                
                // Add the Process object to the queue and increment the process ID and count
                processQueue.add(processes);
                processID++;
                count++;

            }
            scanner.close();

        } 
        catch(FileNotFoundException e) 
        {
            System.out.println("File not found.");
            e.printStackTrace();
        }

        return processQueue;
 
    }

    /**
     * The main method responsible for executing the CPU scheduling algorithms based on user input.
     *
     * @param args Command-line arguments (not used in this case).
     */
    public static void main (String[] args)
    {
        // Read the input file and create a queue of processes
        Queue<Process> processQueue = readFile("cpusched/Datafile1-txt.txt");

        // Display the menu and get the user's choice
        Menu menu = new Menu();
        int choice = menu.display();

        // Varialbes to store the output file name and the output string
        String outFile = "";
        String output = "";

        // Call the appropriate scheduling algorithm based on the user's choice
        switch(choice)
        {
            case 1:
                // First in first out
                Fifo fifo= new Fifo();
                output = fifo.fifoScheduling(processQueue);
                outFile = "cpusched/Fifo-Output.txt";
                break;
            case 2:
                // Shortest job first
                Sjf sjf = new Sjf();
                output = sjf.sjfScheduling(processQueue);
                outFile = "cpusched/Sjf-Output.txt";
                break;
            case 3:
                // Non preemptive priority
                Priority priority = new Priority();
                output = priority.priorityScheduling(processQueue);
                outFile = "cpusched/Priority-Output.txt";
                break;
            default:
                // Defaults to first in first out 
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
