package cpusched;

public class Process
{
    private int processID;
    private int arrivalTime;
    private int priority;
    private int burstUnits;

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

    public void decrementBurst()
    {
        if(this.burstUnits > 0)
        {
            this.burstUnits--;
        }

    }
    

   

}
