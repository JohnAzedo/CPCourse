package producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class PoolWithTwoSemaphore implements Pool {
    private final Semaphore producerSemaphore;
    private final Semaphore consumerSemaphore;
    private final BlockingQueue<Integer> dataBuffer;

    public PoolWithTwoSemaphore(int num_threads){
        producerSemaphore = new Semaphore(num_threads, true);
        consumerSemaphore = new Semaphore(0, true);
        dataBuffer = new LinkedBlockingQueue<Integer>();
    }

    @Override
    public Integer getItem() {
        try { consumerSemaphore.acquire(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        int value = dataBuffer.remove();
        producerSemaphore.release();
        return value;
    }

    @Override
    public void putItem(Integer item) {
        try { producerSemaphore.acquire();}
        catch (InterruptedException e) { e.printStackTrace(); }

        dataBuffer.add(item);
        consumerSemaphore.release();
    }
}
