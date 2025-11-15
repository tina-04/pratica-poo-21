package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Commands.product.ProductCommandUpdate;
import upm.etsisi.Control.ControlTicket;

import java.util.LinkedList;

public class TicketCommand extends Command {
    private final LinkedList<Command> listacomandos;

    public TicketCommand(String name) {
        super(name);
        listacomandos = new LinkedList<Command>();
        listacomandos.add(new TicketCommandAdd());
        listacomandos.add(new TicketCommandRemove());
        listacomandos.add(new TicketCommandPrint());
        listacomandos.add(new TicketCommandNew());
        listacomandos.add(new TicketCommandList());
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
