package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;
import upm.etsisi.View.ViewApp;

public class HelpCommand extends Command {
    private ViewApp viewApp;
    public HelpCommand(String name){
        super(name);
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args.length==1){
            args.equals(new String[]{"help"});
            ViewApp.printHelp();
            result = true;
        }

        return result;
    }
}
