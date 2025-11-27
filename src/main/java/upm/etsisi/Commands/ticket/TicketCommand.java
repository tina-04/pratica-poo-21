package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;

import java.util.LinkedList;

public class TicketCommand extends Command {
    private final LinkedList<Command> listCommands;

    public TicketCommand(String name) {
        super(name);
        listCommands = new LinkedList<Command>();
        listCommands.add(new TicketCommandAdd());
        listCommands.add(new TicketCommandRemove());
        listCommands.add(new TicketCommandPrint());
        listCommands.add(new TicketCommandNew());
        listCommands.add(new TicketCommandList());
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;

        if(args[0].equals("ticket") && args.length >= 2){
            for(int i = 0; i < listCommands.size(); i++){
                result = result || listCommands.get(i).apply(args);
            }
        }
        return result;
    }
}
