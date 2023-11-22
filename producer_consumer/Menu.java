package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu 
{
    @SuppressWarnings("resource")
    public static List<String> selectFile()
    {
        List<String> files = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select an input file(1 - 3):");
        System.out.println("1. Input-4sec-Wait.txt");
        System.out.println("2. Input-6sec-Wait.txt");
        System.out.println("3. Input-8sec-Wait.txt");

        int choice = scanner.nextInt();
        String inFile = "";
        String outFile = "";

        switch(choice)
        {
            case 1:
                inFile = "producer_consumer/Input-4sec-Wait.txt";
                outFile = "producer_consumer/Output-4sec-Wait.txt";
                break;
            case 2:
                inFile = "producer_consumer/Input-6sec-Wait.txt";
                outFile = "producer_consumer/Output-6sec-Wait.txt";
                break;
            case 3:
                inFile = "producer_consumer/Input-8sec-Wait.txt";
                outFile = "producer_consumer/Output-8sec-Wait.txt";
                break;
            default:
                System.out.println("Invalid choice. Using default input file.");
                inFile = "producer_consumer/Input-4sec-Wait.txt";
                outFile = "producer_consumer/Output-4sec-Wait.txt";
                break;
        }
        files.add(inFile);
        files.add(outFile);
        return files;
    }
}
