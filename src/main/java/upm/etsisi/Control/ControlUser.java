package upm.etsisi.Control;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Client;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.User;

import java.util.ArrayList;
import java.util.List;

public class ControlUser {

    //private List<Client> clients;
    //private List<Cashier> cashiers;

    private List<User> clients;
    private List<User> cashiers;

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
    public void addClient(String name, String DNI, String email, String cashId ) {//TODO: Comprobaciones al añadir
        User user = new User(name, DNI, email, cashId);
        clients.add(user);
    }

    public void removeClient(String id) {
        for (User user : this.clients) {
            if (user.getId().equals(id)){
                this.clients.remove(user);
            }
        }
    }

    public void clientList(){} //TODO: Crear ViewClient?


    public void addCashier(String id, String name, String email) {//TODO: Comprobaciones al añadir
        User user = new User(id, name, email);
        clients.add(user);
    }

    public void removeCashier(String id) {
        for (User user : this.cashiers) {
            if (user.getId().equals(id)){
                this.cashiers.remove(user);
            }
        }
    }

    public void cashierList(){} //TODO: Crear ViewCashier?

    public void cashTicket(int id){}
}
