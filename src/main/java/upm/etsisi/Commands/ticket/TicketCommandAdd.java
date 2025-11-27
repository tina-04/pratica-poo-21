package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;

public class TicketCommandAdd extends Command {
    public  TicketCommandAdd() {
        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("add")){

        }
        return false;
    }
}
