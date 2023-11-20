package PRODUCER_CONSUMER;

public class TestCase 
{
    private int sleepTime;
    private int numProducers;
    private int numConsumers;

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

    public void setSleepTime(int sleepTime)
    {
        this.sleepTime = sleepTime;
    }

    public int getNumProducers() 
    {
        return numProducers;
    }

    public void setNumProducers(int numProducers)
    {
        this.numProducers = numProducers;
    }

    public int getNumConsumers() 
    {
        return numConsumers;
    }

    public void setNumConsumers(int numConsumers)
    {
        this.numConsumers = numConsumers;
    }

    @Override
    public String toString() {
        return "TestCase{" +
                "sleepTime=" + sleepTime +
                ", numProducers=" + numProducers +
                ", numConsumers=" + numConsumers +
                '}';
    }
}