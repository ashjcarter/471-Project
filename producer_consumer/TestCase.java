package producer_consumer;

/**
 * This class is used to encapsulate the data for each test case.
 */
public class TestCase 
{
    // Keep track of the number of test cases
    private static int numOfCases = 0; 

    // Instance variables for the test case
    private int sleepTime = 0;
    private int numProducers = 0;
    private int numConsumers = 0;
    private int testCaseID = 0;
    private String inFile = "";
    private String outFile = "";
    
    /**
     * Constructor for the TestCase class.
     * 
     * @param testCaseID    Unique identifier for the test case.
     * @param sleepTime     The sleep time for the test case.
     * @param numProducers  The number of producers for the test case.
     * @param numConsumers  The number of consumers for the test case.
     * @param inFile        The input file name for the test case.
     * @param outFile       The output file name for the test case.
     */
    public TestCase(int testCaseID, int sleepTime, int numProducers, int numConsumers,
                    String inFile, String outFile) 
    {
        // Increment the number of test cases and assign to current ID
        this.testCaseID = ++numOfCases; 
        
        this.sleepTime = sleepTime;
        this.numProducers = numProducers;
        this.numConsumers = numConsumers;
        this.inFile = inFile;
        this.outFile = outFile;
    }

    /**
     * @return The testCaseID.
     */
    public int getTestCaseID()
    {
        return this.testCaseID;
    }

    /**
     * Sets the testCaseID for the test case.
     * 
     * @param testCaseID   The testCaseID is set.
     */ 
    public void setTestCaseID(int testCaseID)
    {
        this.testCaseID = testCaseID;
    }

    /**
     * @return The sleep time for the test case.
     */
    public int getSleepTime() 
    {
        return sleepTime;
    }

    /**
     * Sets the sleep time for the test case.
     * 
     * @param sleepTime The sleep time to set.
     */
    public void setSleepTime(int sleepTime)
    {
        this.sleepTime = sleepTime;
    }

    /**
     * @return The number of producers for the test case.
     */
    public int getNumProducers() 
    {
        return numProducers;
    }

    /**
     * Sets the number of producers for the test case.
     * 
     * @param numProducers The number of producers to set.
     */
    public void setNumProducers(int numProducers)
    {
        this.numProducers = numProducers;
    }

    /**
     * @return The number of consumers for the test case.
     */
    public int getNumConsumers() 
    {
        return numConsumers;
    }

    /**
     * Sets the number of consumers for the test case.
     * 
     * @param numConsumers The number of consumers to set.
     */
    public void setNumConsumers(int numConsumers)
    {
        this.numConsumers = numConsumers;
    }

    /**
     * @return The input file name for the test case.
     */
    public String getInFile()
    {
        return inFile;
    }

    /**
     * Sets the input file name for the test case.
     * 
     * @param fileName The input file name to set.
     */
    public void setInFile(String fileName)
    {
        this.inFile = fileName;
    }

    /**
     * @return The output file name for the test case.
     */
    public String getOutFile()
    {
        return outFile;
    }

    /**
     * Sets the output file name for the test case.
     * 
     * @param fileName The output file name to set.
     */
    public void setOutFile(String fileName)
    {
        this.outFile = fileName;
    }
}