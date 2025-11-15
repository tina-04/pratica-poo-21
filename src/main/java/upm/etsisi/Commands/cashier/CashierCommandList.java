package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommandList extends Command {
    public CashierCommandList() {
        super("list");
    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
