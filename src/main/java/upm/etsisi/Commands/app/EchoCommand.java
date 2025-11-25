package upm.etsisi.Commands.app;

import upm.etsisi.Commands.Command;
import upm.etsisi.View.View;
import upm.etsisi.View.ViewApp;

import java.util.Arrays;

public class EchoCommand  extends Command {
    private ViewApp viewApp;
    public EchoCommand(String name) {
        super(name);
        this.viewApp = new ViewApp();
    }

    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[0].equals("echo")){
            viewApp.print(String.join(" ", Arrays.copyOfRange(args, 1, args.length)));
            result = true;
        }
        return result;
    }
}
