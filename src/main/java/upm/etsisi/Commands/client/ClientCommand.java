package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;

import java.util.LinkedList;

public class ClientCommand extends Command {
    private final LinkedList<Command> listcommands;

    public ClientCommand(String name) {
        super(name);
        listcommands = new LinkedList<Command>();
        listcommands.add(new ClientCommandAdd());
        listcommands.add(new ClientCommandRemove());
        listcommands.add(new ClientCommandList());
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
