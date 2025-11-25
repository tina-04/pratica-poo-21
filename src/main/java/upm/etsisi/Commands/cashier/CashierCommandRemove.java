package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

import java.util.Arrays;

public class CashierCommandRemove extends Command {
    public CashierCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[1].equals("remove")){
            if (args[2] != null) {
                result = ControlCashier.getInstance().removeCashier(args[2]);
            }
        }
        return result;
    }
}
