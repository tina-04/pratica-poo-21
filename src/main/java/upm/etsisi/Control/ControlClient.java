package upm.etsisi.Control;

import upm.etsisi.Model.Client;
import upm.etsisi.Model.Ticket;
import upm.etsisi.View.ViewClient;
import upm.etsisi.View.ViewTicket;

import java.util.ArrayList;
import java.util.List;

public class ControlClient {
    private List<Client> clientsList;
    private List<Ticket> ticketList;
    private ViewClient viewClient;
    private static ControlClient instance;

    public static ControlClient getInstance() {
        if (instance == null) {
            instance = new ControlClient();
        }
        return instance;
    }

    private ControlClient() {
        this.clientsList = new ArrayList<>();
        this.viewClient = new ViewClient();
        this.ticketList = new ArrayList<>();
    }

    public boolean addTicket(String clienId,Ticket ticket){
        boolean resul =false;
        if(existClient(clienId)){
            for(int i =0; i<clientsList.size(); i++){
                if(clientsList.get(i).getCashierId().equals(clienId)){
                    ticketList.add(ticket);
                }
            }
        }
        return  resul;
    }
    public boolean addClient(String name, String DNI, String email, String cashierId) {
        boolean resul = false;
        if (!existClient(DNI) && ControlCashier.getInstance().existCashier(cashierId)) {
            Client client = new Client(name, DNI, email, cashierId);
            clientsList.add(client);
            viewClient.printClient(client);
            viewClient.createOK();
            resul = true;
        }

        return resul;
    }

    public boolean existClient(String DNI) {
        boolean result = false;
        for (Client c : clientsList) {
            if (c.getDNI().equals(DNI))
                result = true;
        }
        return result;
    }

    public Client searchClient(String DNI) {
        Client client = null;
        for (int i = 0; i < clientsList.size(); i++) {
            if (clientsList.get(i).getDNI().equals(DNI)) {
                client = clientsList.get(i);
            }

        }
        return client;
    }

    public boolean removeClient(String DNI) {
        boolean result = false;
        if (existClient(DNI)) {
            Client client = searchClient(DNI);
            clientsList.remove(client);
            viewClient.removeOK();
            result = true;
        }
        return result;
    }

    public void clientList() {
        viewClient.listClient(clientsList);
        viewClient.listOK();
    }
}
