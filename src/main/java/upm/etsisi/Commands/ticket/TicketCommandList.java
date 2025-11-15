package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;


public class TicketCommandList extends Command {
    public  TicketCommandList() {
        super("list");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
