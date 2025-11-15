package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommandRemove extends Command {
    public CashierCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
