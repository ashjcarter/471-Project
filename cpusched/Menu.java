package cpusched;

import java.util.Scanner;

public class Menu 
{ public int display()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a scheduling algorithm:");
        System.out.println("1. First Come First Serve");
        System.out.println("2. Shortest Job First");
        System.out.println("3. Priority(non preemptive)");

        int choice = scanner.nextInt();
        scanner.close();       

        return choice;
    }
    
}
