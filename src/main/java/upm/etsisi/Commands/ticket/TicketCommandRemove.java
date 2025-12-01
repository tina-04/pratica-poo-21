package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;


public class TicketCommandRemove extends Command {
    public  TicketCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {

        boolean result=false;
        if (args[1].equals("remove") && args.length == 5) {
            ControlTicket.getInstance().removeProduct(args[2], args[3], args[4]);
            result = true;
        }
        return result;
    }
}
