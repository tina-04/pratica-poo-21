package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Utility.Utility;


public class TicketCommandPrint extends Command {
    public  TicketCommandPrint() {
        super("print");
    }
    @Override
    public boolean apply(String[] args) {

        boolean result = false;
        if(args.length == 4 && args[1].equals("print")){
            if(Utility.correctCashierId(args[3])){
                ControlTicket.getInstance().printTicket((args[2]), args[3]);
                result = true;
            }

        }
        return result;
    }
}
