package ticket;

public class TicketOffice extends Thread{

    private TicketCounter counter;
    private String pid;
    private final int NUM_PER_OFFICE = 100;

    public TicketOffice(TicketCounter counter, String pid){
        this.counter = counter;
        this.pid = pid;
    }

    @Override
    public void run() {
        try{
            for(int i=0; i<NUM_PER_OFFICE; i++){
                Thread.sleep(100);
                printTicket(counter.getNext());
            }
        } catch (Exception e) {
            // Do something
        }
    }

    private void printTicket(int ticket){
        System.out.printf("Office %s: Ticket %d%n", this.pid, ticket);
    }
}
