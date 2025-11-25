package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

import java.util.Arrays;

public class CashierCommandList extends Command {
    public CashierCommandList() {
        super("list");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[1].equals("list")){
            ControlCashier.getInstance().cashierList();
            result = true;
        }
        return result;
    }
}
