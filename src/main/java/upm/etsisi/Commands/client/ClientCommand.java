package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

public class ClientCommand extends Command {

    public ClientCommand(String name) {
        super(name);
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
