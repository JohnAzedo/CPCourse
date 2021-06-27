package P03;

public class Main{
    public static void main(String[] args){
        final int NUMBER = 10;

        final Semaphores semaphores = new Semaphores(NUMBER);
        
        Producer producer1 = new Producer(semaphores);
        Consumer consumer1 = new Consumer(semaphores);

        producer1.start();
        consumer1.start();

        Producer producer2 = new Producer(semaphores);
        Consumer consumer2 = new Consumer(semaphores);

        producer2.start();
        consumer2.start();

        Producer producer3 = new Producer(semaphores);
        Consumer consumer3 = new Consumer(semaphores);

        producer3.start();
        consumer3.start();
    }

}
