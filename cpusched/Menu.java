package cpusched;

import java.util.Scanner;

/**
 * This class represents a menu for selecting a scheduling algorithm.
 */
public class Menu 
{ 
    /**
     * This method displays a menu to the user and prompts them to select a scheduling algorithm.
     * It reads the user's choice from the console and returns it.
     *
     * @return The user's choice of scheduling algorithm.
     */
    public int display()
    {
        Scanner scanner = new Scanner(System.in);
        
        // Display the menu options
        System.out.println("Select a scheduling algorithm:");
        System.out.println("1. First Come First Serve");
        System.out.println("2. Shortest Job First");
        System.out.println("3. Priority(non preemptive)");

        // Read the user's choice
        int choice = scanner.nextInt();
        scanner.close(); 
        
        System.out.println("Starting scheduling...");

        return choice;
    }
    
}
