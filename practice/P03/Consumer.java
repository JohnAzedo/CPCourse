package P03;

class Consumer extends Thread{
    
    private Semaphores semaphores;

    Consumer(Semaphores semaphores){
        this.semaphores = semaphores;
    }

    void readNumber() throws InterruptedException{
        while(true){
            this.semaphores.consumer.acquire();
            Thread.sleep(Shared.INTERNAL);
            
            this.semaphores.mutex.lock();
            Double number = Shared.deque.removeLast();
            this.semaphores.mutex.unlock();

            System.out.println("Read number: " + number);
            System.out.println("Buffer size: " + Shared.deque.size());  
            this.semaphores.producer.release();
        }
    }

    @Override
    public void run() {
       try {
        readNumber();
    } catch (InterruptedException e) {
        e.printStackTrace();
    };
    }

}
