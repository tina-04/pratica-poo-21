package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlTicket;

public class TicketCommandNew extends Command {
    public  TicketCommandNew() {
        super("new");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("new")){
            if (args.length == 4){
                ControlTicket.getInstance().newTicket(args[2], args[3]);
            } else if (args.length == 5) {
                ControlTicket.getInstance().newTicket(args[2], args[3], args[4]);
            }
            else return false;
            result = true;
        }
        return result;
    }
}
