package upm.etsisi.Control;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Client;
import upm.etsisi.Model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class ControlUser {

    private List<Client> clients;
    private List<Cashier> cashiers;
    private List<Ticket> tickes;
    private static ControlUser instance;
    public static ControlUser getInstance() {
        if (instance == null) {
            instance = new ControlUser();
        }
        return instance;
    }
    private ControlUser() {
        this.clients = new ArrayList<>();
        this.cashiers = new ArrayList<>();
    }
    public void addClient(String name,String DNI, int cashId ) {

    }
    public void removeClient(int id) {
    }
    public void clientList(){

    }
    public void addCashier(int id, String name, String email) {

    }
    public void cashiedList(){

    }

    public void cashTikcet(int id){

    }


}
