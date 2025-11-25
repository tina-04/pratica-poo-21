package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;

import java.util.LinkedList;

public class CashierCommand extends Command {
    private final LinkedList<Command> listCommands;

    public CashierCommand(String name) {
        super(name);
        listCommands = new LinkedList<Command>();
        listCommands.add(new CashierCommandAdd());
        listCommands.add(new CashierCommandRemove());
        listCommands.add(new CashierCommandList());
        listCommands.add(new CashierCommandTicket());
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[0].equals("cash") && args.length >=2){
            for(int i = 0; i < listCommands.size(); i++){
                result = result || listCommands.get(i).apply(args);
            }
        }
        return result;
    }
}
