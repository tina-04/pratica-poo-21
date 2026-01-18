package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlProduct;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Utility.Utility;


public class TicketCommandRemove extends Command {
    public  TicketCommandRemove() {
        super("remove");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result=false;
        if (args[1].equals("remove") && args.length == 5) {
            try{
                String ticketId = args[2];
                String cashId = args[3];
                String prodId = args[4];
                if(Utility.correctCashierId(cashId)){
                    ControlTicket.getInstance().removeProduct(ticketId, cashId, prodId);
                    result = true;
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        return result;
    }
}
