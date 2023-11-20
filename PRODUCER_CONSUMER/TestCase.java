package PRODUCER_CONSUMER;

public class TestCase 
{
    private final int sleepTime;
    private final int numProducers;
    private final int numConsumers;

    public TestCase(int sleepTime, int numProducers, int numConsumers) 
    {
        this.sleepTime = sleepTime;
        this.numProducers = numProducers;
        this.numConsumers = numConsumers;
    }

    public int getSleepTime() 
    {
        return sleepTime;
    }

    public int getNumProducers() 
    {
        return numProducers;
    }

    public int getNumConsumers() 
    {
        return numConsumers;
    }
}