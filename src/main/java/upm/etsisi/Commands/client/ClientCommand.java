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
        boolean result = false;
        if (args[0].equals("client")&&args.length>=2) {
            for (int i = 0; i < listCommands.size(); i++) {
                result=result || listCommands.get(i).apply(args);
            }
        }
        return result;
    }
}
