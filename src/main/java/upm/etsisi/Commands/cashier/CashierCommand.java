package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommand extends Command {

    public CashierCommand(String name) {
        super(name);

    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
