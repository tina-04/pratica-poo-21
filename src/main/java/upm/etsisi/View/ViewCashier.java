package upm.etsisi.View;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Ticket;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ViewCashier  implements View{
    public void messageOutput(String output){
        System.out.println(output);
    }
    public void printCashier(Cashier cashier){
        messageOutput("Cash{identifier=‘" + cashier.getCashierId()+ "’, name=''" + cashier.getName()+
                "', email=‘" + cashier.getEmail()+ "'}");

    }
    public void listCashier(List<Cashier> cashiers){
        for(Cashier cashier : cashiers){
            printCashier(cashier);
        }
    }
    public void listTicket(List<Ticket> ticket){
        Collections.sort(ticket, (t1, t2) -> t1.getId().compareToIgnoreCase(t2.getId()));
        for(int i = 0; i < ticket.size(); i++){
            messageOutput("ticket id: " + ticket.get(i).getId() + ", status: " + ticket.get(i).getStatus());
        }

    }
    public void addOk(){
        messageOutput("cash add:ok");
    }
    public void removeOk(){
        messageOutput("cash remove:ok");
    }
    public void listOk(){
        messageOutput("cash list:ok");
    }
    public void tickestOk(){
        messageOutput("cash tickets:ok");
    }

}
