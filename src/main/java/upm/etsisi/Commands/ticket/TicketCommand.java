package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlTicket;

import java.util.LinkedList;

public class TicketCommand extends Command {
    private final LinkedList<Command> listacomandos;

    public TicketCommand() {
        super("ticket");
        listacomandos = new LinkedList<Command>();
        listacomandos.add(new TicketCommandAdd());
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
