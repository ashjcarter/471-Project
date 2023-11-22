package cpusched;

/**
 * This class represents a process in a CPU scheduling algorithm.
 */
public class Process
{
    private int processID;
    private int arrivalTime;
    private int priority;
    private int burstUnits;

   /**
    * Constructs a new Process object.
    *
    * @param processID   The ID of the process
    * @param arrivalTime The arrival time of the process
    * @param burstUnits  The burst units required by the process
    * @param priority    The priority of the process
    */
    public Process(int processID, int arrivalTime, int burstUnits, int priority)
    {
        this.processID = processID;
        this.arrivalTime = arrivalTime;
        this.priority = priority;
        this.burstUnits = burstUnits;
    }
    
   /**
    * Returns the ID of the process.
    *
    * @return The process ID
    */
    public int getProcessID()
    {
        return processID;
    }

   /**
    * Sets the ID of the process.
    *
    * @param processID The process ID to set
    */
    public void setProcessID(int processID)
    {
        this.processID = processID;
    }

   /**
    * Returns the arrival time of the process.
    *
    * @return The arrival time
    */
    public int getArrivalTime()
    {
        return arrivalTime;
    }

   /**
    * Sets the arrival time of the process.
    *
    * @param arrivalTime The arrival time to set
    */
    public void setArrivalTime(int arrivalTime)
    {
        this.arrivalTime = arrivalTime;
    }

   /**
    * Returns the priority of the process.
    *
    * @return The priority
    */
    public int getPriority()
    {
        return priority;
    }

   /**
    * Sets the priority of the process.
    *
    * @param priority The priority to set
    */
    public void setPriority(int priority)
    {
        this.priority = priority;
    }

   /**
    * Returns the burst units required by the process.
    *
    * @return The burst units
    */
    public int getBurstUnits()
    {
        return burstUnits;
    }

   /**
    * Sets the burst units required by the process.
    *
    * @param burstUnits The burst units to set
    */
    public void setBurstUnits(int burstUnits)
    {
        this.burstUnits = burstUnits;
    }

   /**
    * Decrements the burst units by 1.
    * If the burst units become less than or equal to 0, 
    * The process is considered finished.
    */
    public void decrementBurst()
    {
        if(this.burstUnits > 0)
        {
            this.burstUnits--;
        }

    }
    

   

}
