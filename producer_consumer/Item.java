package producer_consumer;

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

    public double getProductionTime() 
    {
        return productionTime;
    }
}
