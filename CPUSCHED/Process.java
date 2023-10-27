package CPUSCHED;

public class Process
{
    private int processID;
    private int arrivalTime;
    private int priority;
    private int burstUnits;
    private int startTime;
    private int endTime;

    public Process(int processID, int arrivalTime, int burstUnits, int priority)
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

    public void setPriority(int priority)
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

    public int getEndtime()
    {
        return endTime;
    }

    public void setEndTime(int endTime)
    {
        this.endTime = endTime;
        
    }

}
