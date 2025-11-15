package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ClientCommandAdd extends Command {

    public ClientCommandAdd() {

        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
