package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;

public class TicketCommandNew extends Command {
    public  TicketCommandNew() {
        super("new");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("new")){
            if (args.length == 4){

            }
        }
        return false;
    }
}
