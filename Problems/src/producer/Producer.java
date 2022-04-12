package producer;

import java.util.Random;

public class Producer extends Thread{
    private Pool pool;
    private String pid;

    public Producer(String pid, Pool pool){
        this.pool = pool;
        this.pid = pid;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true){
            try {
                int value = random.nextInt();
                pool.putItem(value);
                System.out.printf("Producer %s: Create %d%n", this.pid, value);
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
