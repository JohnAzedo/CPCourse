package producer;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

public class Pool {
    private final Semaphore available;
    private final ArrayList<Integer> dataBuffer;
    private final int CAPACITY = -1;

    public Pool(){
        available = new Semaphore(CAPACITY, true);
        dataBuffer = new ArrayList<Integer>();
    }

    public void putItem(Integer item){
        if(dataBuffer.add(item)) available.release();
    }

    public Integer getItem() throws InterruptedException {
        available.acquire();
        return dataBuffer.remove(dataBuffer.size()-1);
    }
}
