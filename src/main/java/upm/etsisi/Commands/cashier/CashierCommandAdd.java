package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;
import upm.etsisi.Model.Cashier;

public class CashierCommandAdd extends Command {
    public CashierCommandAdd() {
        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
