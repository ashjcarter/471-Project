package CPUSCHED;

public class Process
{
    private int processID;
    private int arrivalTime;
    private int priority;
    private int burstUnits;
    private int startTime;
    private int finishedTime;

    public Process(int processID, int arrivalTime, int priority, int burstUnits)
    {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burstUnits = burstUnits;
    }
    
    public int getProcessID()
    {
        return processID;
    }

    public void setProcessID(int processID)
    {
        this.processID = processID;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

    public int getPriority()
    {
        return priority;
    }

    public void getPriority(int priority)
    {
        this.priority = priority;
    }

    public int getBurstUnits()
    {
        return burstUnits;
    }

    public void setBurstUnits(int burstUnits)
    {
        this.burstUnits = burstUnits;
    }

    public int getStartTime()
    {
        return startTime;
    }

    public void setStartTime(int startTime)
    {
        this.startTime = startTime;
    }

    public int getFinishedTime()
    {
        return finishedTime;
    }

}
