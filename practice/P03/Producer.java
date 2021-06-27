package P03;

class Producer extends Thread{
    
    private Semaphores semaphores;

    Producer(Semaphores semaphores){
        this.semaphores = semaphores;
    }

    public Double generateNumber(){
        return Math.random() * 10;
    }

    public void writeNumber() throws InterruptedException{
        while(true){
            this.semaphores.producer.acquire();
            Thread.sleep(Shared.INTERNAL);
            this.semaphores.mutex.lock();
            Shared.deque.addFirst(generateNumber());
            this.semaphores.mutex.unlock();
            this.semaphores.consumer.release();
        }
    }

    public void run(){
        try {
            writeNumber();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    

}
