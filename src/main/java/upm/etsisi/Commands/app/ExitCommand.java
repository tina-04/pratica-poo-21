package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;

public class ExitCommand extends Command {
    public ExitCommand(String name) {
        super("exit");
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[0].equals("exit")){
            result = true;

        }
        return result;
    }
}
