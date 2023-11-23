package producer_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class provides a menu for user to select an input file.
 */
public class Menu 
{
    /**
     * This method allows the user to select an input file from a given list 
     * and returns the corresponding input and output file names.
     * 
     * @return A List of Strings where the first element is the input file name 
     *         and the second element is the output file name.
     */
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

        // Switch case to handle user input
        switch(choice)
        {
            // Read from Input-4sec-Wait.txt and write to Output-4sec-Wait.txt
            case 1:
                inFile = "producer_consumer/Input-4sec-Wait.txt";
                outFile = "producer_consumer/Output-4sec-Wait.txt";
                break;

            // Read from Input-6sec-Wait.txt and write to Output-6sec-Wait.txt
            case 2:
                inFile = "producer_consumer/Input-6sec-Wait.txt";
                outFile = "producer_consumer/Output-6sec-Wait.txt";
                break;

            // Read from Input-8sec-Wait.txt and write to Output-8sec-Wait.txt
            case 3:
                inFile = "producer_consumer/Input-8sec-Wait.txt";
                outFile = "producer_consumer/Output-8sec-Wait.txt";
                break;

            // Default case returns Input-4sec-Wait.txt and write to Output-4sec-Wait.txt
            default:
                System.out.println("Invalid choice. Using default input file.");
                inFile = "producer_consumer/Input-4sec-Wait.txt";
                outFile = "producer_consumer/Output-4sec-Wait.txt";
                break;
        }
        // Add input and output file names to the list
        files.add(inFile);
        files.add(outFile);

        // Return the list of input and output file names
        return files;
    }
}
