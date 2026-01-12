package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Utility.Utility;

public class TicketCommandAdd extends Command {
    public  TicketCommandAdd() {
        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("add")){

            String[] personalization = null;
            if (args.length > 6) {
                personalization = new String[args.length - 6];
                for (int i = 0; i < personalization.length; i++) {
                    personalization[i] = args[i + 6];
                }
            }
            if(args .length > 5){
                if(Utility.correctCashierId(args[3])){
                    ControlTicket.getInstance().addProduct(args[2], args[3], args[4], args[5], personalization);
                    result = true;
                }
            }else{

                ControlTicket.getInstance().addProduct(args[2], args[3], args[4], null, personalization);
            }


        }

        return result;
    }
}
