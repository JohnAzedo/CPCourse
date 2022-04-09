import ticket.TicketCounter;
import ticket.TicketOffice;

public class Main {
    public static void main(String [] args) {
        ticketApplication();
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
}
