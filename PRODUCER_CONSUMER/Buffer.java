package PRODUCER_CONSUMER;

// Buffer class
public class Buffer {
    private static final int BUFFER_SIZE = 5;
    private final Object[] buffer = new Object[BUFFER_SIZE];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void insertItem(Object item) {
        while (count == BUFFER_SIZE) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        buffer[in] = item;
        in = (in + 1) % BUFFER_SIZE;
        count++;
        notifyAll();
    }

    public synchronized Object removeItem() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        Object item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;
        notifyAll();
        return item;
    }

    class Item
    {
        int value = 0;
        long producedTime = 0;
        long consumedTime = 0;

        Item(int value)
        {
            this.value = value;
            this.producedTime = System.currentTimeMillis();

        }
    }
}