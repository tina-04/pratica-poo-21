package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;

public class ClientCommandRemove extends Command {
    public ClientCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("remove")) {
            ControlProduct.getInstance().removeProduct(Integer.parseInt(args[4]));
            result= true;
        }
        return result;
    }
}
