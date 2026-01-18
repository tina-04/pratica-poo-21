package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Utility.Utility;


public class TicketCommandPrint extends Command {
    public  TicketCommandPrint() {
        super("print");
    }
    @Override
    public boolean apply(String[] args) {

        boolean result = false;
        if(args.length == 4 && args[1].equals("print")){
            try{
                String ticketId= args[2];
                String cashId =args[3];
                if(Utility.correctCashierId(cashId)){

                    ControlTicket.getInstance().printTicket(ticketId,cashId);
                    result = true;
                }
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
