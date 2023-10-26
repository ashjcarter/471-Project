package CPUSCHED;

public class Process
{
    private int processID;
    private int arrivalTime;
    private int priority;
    private int burstUnits;

    public Process(int processID, int arrivalTime, int priority, int burstUnits)
    {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burstUnits = burstUnits;
    }
    
}
