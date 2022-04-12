package producer;

public class Consumer extends Thread {
    private Pool pool;
    private String pid;

    public Consumer(String pid, Pool pool){
        this.pid = pid;
        this.pool = pool;
    }

    @Override
    public void run() {
        while (true){
            try {
                int value = pool.getItem();
                System.out.printf("Consumer %s: Get %d%n", this.pid, value);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
