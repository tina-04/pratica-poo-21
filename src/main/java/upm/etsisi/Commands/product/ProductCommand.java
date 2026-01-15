package upm.etsisi.Commands.product;

import upm.etsisi.Commands.Command;

import java.util.LinkedList;

public class ProductCommand extends Command {
    private final LinkedList<Command> listCommands;

    public ProductCommand(String name) {
        super(name);
        listCommands = new LinkedList<Command>();
        listCommands.add(new ProductCommandAdd());
        listCommands.add(new ProductCommandList());
        listCommands.add(new ProductCommandAddFood());
        listCommands.add(new ProductCommandAddMeeting());
        listCommands.add(new ProductCommandRemove());
        listCommands.add(new ProductCommandUpdate());

    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;

        if(args[0].equals("prod") && args.length >=2){
            for(int i = 0; i < listCommands.size(); i++){
                result = result || listCommands.get(i).apply(args);
            }
        }

        return result ;
    }
}
