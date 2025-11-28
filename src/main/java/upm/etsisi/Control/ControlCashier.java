package upm.etsisi.Control;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewCashier;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ControlCashier {

    private List<Cashier> cashierList;
    private List<Ticket> ticketList;
    private ViewCashier viewCashier;
    private static ControlCashier instance;
    public static ControlCashier getInstance() {
        if (instance == null) {
            instance = new ControlCashier();
        }
        return instance;
    }
    private ControlCashier() {
        this.cashierList = new ArrayList<>();
        this.viewCashier = new ViewCashier();
        this.ticketList = new ArrayList<>();
    }

    public boolean addCashier(String id, String name, String email) {
        boolean result = false;
        if(Utility.correctEmail(email) && Utility.correctCashierId(id) && !existCashier(id)){
            Cashier cashier = new Cashier(id, name, email);
            cashierList.add(cashier);
            viewCashier.printCashier(cashier);
            result = true;
        }
        viewCashier.addOk();
        return result;
    }

    public boolean addCashier(String name, String email) {
        String id;
        do {
            id = Utility.generateCashierId();
        } while (existCashier(id));

        return addCashier(id, name, email);
    }

    public boolean existCashier(String id){
        boolean exist = false;
        for(int i =0; i<cashierList.size();i++){
            if(cashierList.get(i).getCashierId().equals(id)){
                exist=true;
            }
        }
        return exist;
    }
    public Cashier searchCashier(String id) {
        Cashier cashier = null;
        for(int i =0;i<cashierList.size();i++) {
            if(cashierList.get(i).getCashierId().equals(id)) {
                cashier = cashierList.get(i);
            }

        }
        return cashier;
    }
    public boolean removeCashier(String id) {
        boolean result = false;
        Cashier cash = searchCashier(id);
        if(cash !=null){
            ControlTicket.getInstance().removeTicker(ticketList);
            cashierList.remove(cash);
            ticketList.clear();
        }
        viewCashier.removeOk();

        return result;
    }
    public boolean addTicket(String cashId,Ticket ticket){
        boolean resul =false;
        if(existCashier(cashId)){
           for(int i =0; i<cashierList.size(); i++){
               if(cashierList.get(i).getCashierId().equals(cashId)){
                  ticketList.add(ticket);
               }
            }
        }
        return  resul;
    }
    public boolean cashTicket(String id){//TODO
        if (existCashier(id)){
            ArrayList<Ticket> tickets = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                if (ticket.getCashierId().equals(id)) {
                    tickets.add(ticket);
                }
            }
            tickets.sort(Comparator.comparing(Ticket::getId));
            viewCashier.listTicket(tickets);
        }
        viewCashier.tickestOk();
        return false;
    }

    public void cashierList(){
      viewCashier.listCashier(cashierList);
      viewCashier.listOk();
    }



}
