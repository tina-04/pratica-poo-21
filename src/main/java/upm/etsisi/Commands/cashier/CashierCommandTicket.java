package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommandTicket extends Command {
    public CashierCommandTicket() {
        super("ticket");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if(args[1].equals("tickets")){
            if (args[2] != null) {
                try{
                    result = ControlCashier.getInstance().cashTicket(args[2]);
                }catch(Exception e){
                    System.out.println(e.getMessage());
                }
            }
        }
        return result;
    }
}
