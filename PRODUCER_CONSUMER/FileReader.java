package PRODUCER_CONSUMER;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileReader 
{
    public static List<TestCase> readFile(String fileName) 
    {
        List<TestCase> testCases = new ArrayList<>();
        try 
        {
            File inputFile = new File(fileName);
            Scanner scanner = new Scanner(inputFile);

            // Skip the header line
            if(scanner.hasNextLine()) 
            {
                scanner.nextLine();
            }

            while(scanner.hasNextLine()) 
            {
                String line = scanner.nextLine();
                System.out.println("Line from file: " + line);
                String[] processFile = line.trim().split("\\s+");

                int sleepTime = Integer.parseInt(processFile[0]);
                int numProducers = Integer.parseInt(processFile[1]);
                int numConsumers = Integer.parseInt(processFile[2]);

                TestCase testCase = new TestCase(sleepTime, numProducers, numConsumers);
                testCases.add(testCase);
                System.out.println("Created TestCase: " + testCase);
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
