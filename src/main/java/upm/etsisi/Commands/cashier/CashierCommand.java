package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

import java.util.LinkedList;

public class CashierCommand extends Command {
    private final LinkedList<Command> listcommands;

    public CashierCommand(String name) {
        super(name);
        listcommands = new LinkedList<Command>();
        listcommands.add(new CashierCommandAdd());
        listcommands.add(new CashierCommandRemove());
        listcommands.add(new CashierCommandList());
        listcommands.add(new CashierCommandTicket());
    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
