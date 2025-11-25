package upm.etsisi.Control;

import upm.etsisi.Model.Cashier;
import upm.etsisi.Model.Client;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.User;
import upm.etsisi.View.ViewClient;

import java.util.ArrayList;
import java.util.List;

public class ControlUser {

    private List<Client> clientsList;
    private List<Cashier> cashiers;
    private List<Ticket> tickes;
    private ViewClient viewUser;
    private static ControlUser instance;
    public static ControlUser getInstance() {
        if (instance == null) {
            instance = new ControlUser();
        }
        return instance;
    }
    private ControlUser() {
        this.clientsList = new ArrayList<>();
        this.cashiers = new ArrayList<>();
        this.viewUser = new ViewClient();
    }
    public void addClient(String name,String DNI,String email, String cashierId ) {


    }
    public boolean existClient(String DNI) {
        boolean result = false;
        for(Client c : clientsList) {
            if(c.getDNI().equals(DNI))
                result = true;
        }
        return result;
    }
    public Client searchClient(String DNI) {
        for(Client c : clientsList) {
            if(c.getDNI().equals(DNI)) {
                return c;
            }

        }
        return null;
    }
    public void removeClient(String DNI){
        if(existClient(DNI)){
            Client client = searchClient(DNI);
            clientsList.remove(client);
        }

    }


}
