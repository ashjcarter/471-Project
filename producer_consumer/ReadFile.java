package producer_consumer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class ReadFile provides a method to read data from a file.
 */
public class ReadFile 
{
    /**
     * This method reads the file specified by the filename, processes the data, 
     * and returns a List of TestCase objects.
     * Each line in the file (except for the header) is converted into a TestCase object.
     * 
     * @param fileName The name of the file to read.
     * @return         A List of TestCase objects.
     */
    public static List<TestCase> readFile(String fileName) 
    {
        List<TestCase> testCases = new ArrayList<>();
        try 
        {
            File inFile = new File(fileName);
            Scanner scanner = new Scanner(inFile);

            // Skip the header line
            if(scanner.hasNextLine()) 
            {
                scanner.nextLine();
            }
            int testCaseID = 0;

            // Process each line in the file
            while(scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                testCaseID++;
                
                // Split the line into an array of strings
                String[] processFile = line.trim().split("\\s+");

                // Convert the strings to integers
                int sleepTime = Integer.parseInt(processFile[0]);
                int numProducers = Integer.parseInt(processFile[1]);
                int numConsumers = Integer.parseInt(processFile[2]);

                // Create a TestCase object and add it to the list
                TestCase testCase = new TestCase(testCaseID,sleepTime, numProducers, 
                                        numConsumers, fileName, fileName);
                testCases.add(testCase);
            }
            scanner.close();

        } 
        catch(FileNotFoundException e) 
        {
            System.out.println("Input file not found.");
            e.printStackTrace();
        }
        // Return the list of test cases
        return testCases;
    }
}
