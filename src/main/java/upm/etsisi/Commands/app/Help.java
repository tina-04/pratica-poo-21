package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;
import upm.etsisi.Commands.IComando;

public class Help extends Command {
    public Help(){
        super("help");
    }

    @Override
    public boolean apply(String[] args) {
        return false;
    }
}
