package ticket;

public class TicketCounter {

    private int ticket;

    public TicketCounter(){
        this.ticket = 0;
    }

    public int getNext(){
        return ticket++;
    }
}
