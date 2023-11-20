package PRODUCER_CONSUMER;

public class Item 
{
    private final int value;
    private final long productionTime;

    public Item(int value) 
    {
        this.value = value;
        this.productionTime = System.currentTimeMillis();
    }

    public int getValue() 
    {
        return value;
    }

    public long getProductionTime() 
    {
        return productionTime;
    }
}
