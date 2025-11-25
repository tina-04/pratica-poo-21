package upm.etsisi.Commands.client;

import upm.etsisi.Commands.Command;

import java.util.LinkedList;

public class ClientCommand extends Command {
    private final LinkedList<Command> listCommands;

    public ClientCommand(String name) {
        super(name);
        listCommands = new LinkedList<Command>();
        listCommands.add(new ClientCommandAdd());
        listCommands.add(new ClientCommandRemove());
        listCommands.add(new ClientCommandList());
    }
    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
