package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ClientCommandRemove extends Command {
    public ClientCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
