package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommandTicket extends Command {
    public CashierCommandTicket() {
        super("ticket");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
