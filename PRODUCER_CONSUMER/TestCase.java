package PRODUCER_CONSUMER;

public class TestCase 
{
    static private int numOfCases = 0;
    private int sleepTime = 0;
    private int numProducers = 0;
    private int numConsumers = 0;
    private int testCaseID = 0;
    private String inFile = "";
    private String outFile = "";

    public TestCase(int testCaseID, int sleepTime, int numProducers, int numConsumers,
    String inFile, String outFile) 
    {
        this.testCaseID = ++numOfCases;
        this.sleepTime = sleepTime;
        this.numProducers = numProducers;
        this.numConsumers = numConsumers;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public int getTestCaseID()
    {
        return this.testCaseID;
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

    public String getInFile()
    {
        return inFile;
    }

    public void setInFile(String fileName)
    {
        this.inFile = fileName;
    }

    public String getOutFile()
    {
        return outFile;
    }

    public void setOutFile(String fileName)
    {
        this.outFile = fileName;
    }
}