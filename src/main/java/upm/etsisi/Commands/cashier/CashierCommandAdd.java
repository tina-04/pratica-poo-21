package upm.etsisi.Commands.cashier;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlCashier;

public class CashierCommandAdd extends Command {
    public CashierCommandAdd() {
        super("add");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("add")) {
            try{
                if (args.length == 5) {
                    String id = args[2];
                    String username = args[3];
                    String email = args[4];
                    result = ControlCashier.getInstance().addCashier(id, username.substring(1, username.length()-1), email);
                } else if (args.length == 4) {
                    String username = args[2];
                    String email = args[3];
                    result = ControlCashier.getInstance().addCashier(username.substring(1, username.length()-1), email);
                }
            } catch (NullPointerException e) {
                System.out.println(e.getMessage());
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
