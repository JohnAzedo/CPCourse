package P01;

import java.util.concurrent.locks.ReentrantLock;

public class Counter extends Thread{
    private int id;

    static ReentrantLock counterLock = new ReentrantLock(true); // enable fairness policy

    public Counter(int id){
        this.id = id;
    }

    @Override
    public String toString(){
        return id + ": " + Main.sum;
    }

    public void incrementCounterWithLock(){
        counterLock.lock();
        try{
            Main.sum++;
            System.out.println(this.toString());
        }finally{
            counterLock.unlock();
        }
    }

    public void incrementCounter(){
        Main.sum++;
        System.out.println(this.toString());
    }

    public void run(){
        incrementCounter();
    }
}
