package PRODUCER_CONSUMER;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Main
{
    // private static final Buffer buffer = new Buffer();
    private static final BlockingQueue<Integer> buffer = new LinkedBlockingQueue<>();
    private static final Random rand = new Random();

    public static class Item
    {
        int value = 0;
        long producedTime = 0;
        long consumedTime = 0;

        public Item(int value)
        {
            this.value = value;
            this.producedTime = System.currentTimeMillis();
        }
    }

    private static Item produceItem()
    {
        // Logic to create a new item
        return new Item((int)(Math.random() * 100));
    }

    private static void producer()
    {
        // Logic to produce items and insert into buffer
        while(true) 
        {
            Item item = produceItem(); // Generate an item
            buffer.insertItem(item);
        }
    }

    private static void consumer()
    {
        // Logic to consume items and remove from buffer
        while(true) 
        {

            Item item = buffer.removeItem();
            item.consumedTime = System.currentTimeMillis();
            System.out.println("Consumed item: " + item);
        }
    }

    public static void main(String[] args)
    {
        Thread producerThread = new Thread(Main::producer);
        producerThread.start();

        Thread consumerThread = new Thread(Main::consumer);
        consumerThread.start();
    }


}


