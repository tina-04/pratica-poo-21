package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;

public class ClientCommandRemove extends Command {
    public ClientCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("remove")) {
            result = ControlClient.getInstance().removeClient((args[2]));
        }
        return result;
    }
}
