package PRODUCER_CONSUMER;

// Buffer class
public class Buffer {
    private static final int BUFFER_SIZE = 5;
    private final Main.Item[] buffer = new Main.Item[BUFFER_SIZE];
    private int count = 0;
    private int in = 0;
    private int out = 0;

    public synchronized void insertItem(Main.Item item) {
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
        Main.Item item = buffer[out];
        out = (out + 1) % BUFFER_SIZE;
        count--;
        notifyAll();
        return item;
    }
}