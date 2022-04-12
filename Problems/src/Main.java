import producer.Consumer;
import producer.Pool;
import producer.Producer;
import ticket.TicketCounter;
import ticket.TicketOffice;

public class Main {
    public static void main(String [] args) {
//        ticketApplication();
        producerApplication();
    }

    private static void ticketApplication(){
        final int NUMBER_OF_OFFICE = 10;
        TicketCounter counter = new TicketCounter();
        TicketOffice office;
        for(int i = 0; i<NUMBER_OF_OFFICE; i++){
            office = new TicketOffice(counter, String.valueOf(i));
            office.start();
        }
    }

    private static void producerApplication(){
        final int NUMBER_OF_THREADS = 3;
        Pool pool = new Pool();
        Producer producer;
        Consumer consumer;
        for(int i=0; i<NUMBER_OF_THREADS; i++){
            producer = new Producer(String.valueOf(i), pool);
            consumer = new Consumer(String.valueOf(i), pool);
            producer.start();
            consumer.start();
        }
    }
}
