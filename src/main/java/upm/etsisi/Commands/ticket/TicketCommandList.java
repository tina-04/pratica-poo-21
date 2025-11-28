package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;


public class TicketCommandList extends Command {
    public  TicketCommandList() {
        super("list");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if( args[1].equals("list") && args.length == 2){
            ControlTicket.getInstance().ticketList();
            result = true;
        }
        return result;
    }
}
