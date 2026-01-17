package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;

public class ExitCommand extends Command {
    public ExitCommand(String name) {
        super("exit");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[0].equals("exit")){
            ControlProduct.getInstance().saveProducts();
            ControlCashier.getInstance().saveCashiers();
            ControlClient.getInstance().saveClients();
            result = true;
        }
        return result;
    }
}
