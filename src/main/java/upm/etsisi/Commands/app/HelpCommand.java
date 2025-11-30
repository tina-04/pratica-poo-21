package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;
import upm.etsisi.View.ViewApp;

public class HelpCommand extends Command {
    public HelpCommand(String name){
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args.length==1 && args[0].equals("help")){
            ViewApp.printHelp();
            result = true;
        }

        return result;
    }
}
