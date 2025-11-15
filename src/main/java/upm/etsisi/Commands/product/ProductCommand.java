package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;
import upm.etsisi.Commands.ticket.TicketCommandAdd;
import upm.etsisi.Control.ControlClient;

import java.util.LinkedList;

public class ProductCommand extends Command {
    private final LinkedList<Command> listcommands;

    public ProductCommand(String name) {
        super(name);
        listcommands = new LinkedList<Command>();
        listcommands.add(new ProductCommandAdd());
        listcommands.add(new ProductCommandList());
        listcommands.add(new ProductCommandAddFood());
        listcommands.add(new ProdcutCommandAddMeeting());
        listcommands.add(new ProdcutCommandRemove());
        listcommands.add(new ProductCommandUpdate());

    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
