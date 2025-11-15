package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ClientCommandList extends Command {
    public ClientCommandList() {
        super("list");
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
