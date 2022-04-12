package producer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;

public class PoolWithOneSemaphore implements Pool {
    private final Semaphore available;
    private final BlockingQueue<Integer> dataBuffer;
    private final int CAPACITY = 0;

    public PoolWithOneSemaphore(){
        available = new Semaphore(CAPACITY, true);
        dataBuffer = new LinkedBlockingQueue<Integer>();
    }

    @Override
    public void putItem(Integer item){
        if(dataBuffer.add(item)) available.release();
    }

    @Override
    public Integer getItem() {
        // There's a problem here,
        // sometimes the 23' line is called first of 22' line
        // SOLUTION: Use a concurrent collection
        try { available.acquire(); }
        catch (InterruptedException e) { e.printStackTrace(); }
        return dataBuffer.remove();
    }
}
