package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;

public class TicketCommandNew extends Command {
    public  TicketCommandNew() {
        super("new");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
