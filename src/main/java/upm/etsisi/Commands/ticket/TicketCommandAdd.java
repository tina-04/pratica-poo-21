package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlTicket;

public class TicketCommandAdd extends Command {
    public  TicketCommandAdd() {
        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("add")){
            String[] personalization = (args.length > 6) ? java.util.Arrays.stream(args, 6, args.length).toArray(String[]::new) : null;
            //ControlTicket.getInstance().add(args[2], args[3], args[4], args[5], personalization);
            result = true;
        }
        return result;
    }
}
