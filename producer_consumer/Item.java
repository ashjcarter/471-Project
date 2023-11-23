package producer_consumer;

/**
 * This class represents an item produced by the producer in the Producer-Consumer problem.
 */
public class Item 
{
    private final int randItem; // The randomly generated number for producers to produce
    private final long productionTime; // The time at which the item was produced

    /**
     * Constructs a new Item with the given value and sets the production time.
     *
     * @param value The generated number of the item
     */
    public Item(int value) 
    {
        this.randItem = value;
        this.productionTime = System.currentTimeMillis(); 
    }

    /**
     * Returns the randomly generated item value of this item.
     *
     * @return The randomly generated item value of this item
     */
    public int getRandItem() 
    {
        return randItem;
    }

    /**
     * Returns the production time of this item.
     * The production time is represented as the number of milliseconds 
     *
     * @return The production time of this item
     */
    public long getProductionTime() 
    {
        return productionTime;
    }
}
