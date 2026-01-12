package upm.etsisi.Commands.ticket;

import upm.etsisi.Commands.Command;
import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Utility.Utility;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TicketCommandNew extends Command {
    public  TicketCommandNew() {
        super("new");
    }
    @Override
    public boolean apply(String[] args) {
        boolean result = false;
        if (args[1].equals("new")){
            try{
                String type = null;

                if(args.length == 4){
                    if(Utility.correctCashierId(args[2]) && Utility.correctDNI(args[3])){
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
                        String date =LocalDateTime.now().format(formatter).toString();
                        StringBuilder s1 = new StringBuilder();
                        s1.append( date).append(Utility.generateId());
                        result =ControlTicket.getInstance().newTicket(s1.toString(),args[2],args[3], type);
                    }
                }else if(args.length == 6){

                        type = args[5].substring(1);

                    if(Utility.correctCashierId(args[3]) && Utility.isNifNumValid(args[4])){
                        result =ControlTicket.getInstance().newTicket(args[2],args[3],args[4], type );
                    }

                }else{
                    if(Utility.correctCashierId(args[3]) && Utility.correctDNI(args[4])){
                        result =ControlTicket.getInstance().newTicket(args[2],args[3],args[4], type );
                    }
                }

            }catch(Exception e ){
                System.out.println(e.getMessage());
            }
        }
        return result;
    }
}
