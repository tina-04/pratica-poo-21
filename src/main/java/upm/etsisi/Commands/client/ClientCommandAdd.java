package upm.etsisi.Commands.client;

import com.sun.jdi.InvalidLineNumberException;
import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlClient;
import upm.etsisi.Utility.Utility;

import javax.naming.InvalidNameException;

public class ClientCommandAdd extends Command {

    public ClientCommandAdd() {

        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result =false;
        if (args[1].equals("add")&& args.length==6) {
            try {
                String name = args[2];
                String id = args[3];
                String email = args[4];
                String cashierId = args[5];
                result = ControlClient.getInstance().addClient(name.substring(1, name.length()-1),id,email, cashierId);

            } catch (NullPointerException e ) {
                System.out.println(e.getMessage());
            }catch( Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
