package PRODUCER_CONSUMER;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReadFile 
{
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

            while(scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                testCaseID++;
                
                String[] processFile = line.trim().split("\\s+");
                int sleepTime = Integer.parseInt(processFile[0]);
                int numProducers = Integer.parseInt(processFile[1]);
                int numConsumers = Integer.parseInt(processFile[2]);

                TestCase testCase = new TestCase(testCaseID,sleepTime, numProducers, numConsumers,
                fileName, fileName);
                testCases.add(testCase);
            }
            scanner.close();

        } 
        catch(FileNotFoundException e) 
        {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return testCases;
    }
}
