package P03;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Semaphores {
    final ReentrantLock mutex;
    final Semaphore consumer;
    final Semaphore producer;

    Semaphores(int number){
        consumer = new Semaphore(0);
        producer = new Semaphore(number);
        mutex = new ReentrantLock();
    }
}
