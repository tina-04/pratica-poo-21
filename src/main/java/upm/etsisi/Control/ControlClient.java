package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewClient;
import upm.etsisi.View.ViewTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ControlClient {
    private List<Client> clientsList;
    private Map<String, ClientAndCompany> cc = new HashMap<>();
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
    public boolean exists(String id) {
        return cc.containsKey(id);
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
    public boolean addClient(String name, String id, String email, String cashierId) {
        boolean resul = false;
        if (!exists(id) && ControlCashier.getInstance().existCashier(cashierId)) {
            if(Utility.correctDNI(id)){
                Client client = new Client(name, id, email, cashierId);
                cc.put(id, client);
                viewClient.printClient(client);
                viewClient.createOK();
                resul = true;
            }else if(Utility.isNifNumValid(id)){
                ClientCompany client = new ClientCompany(name, id, email, cashierId);
                cc.put(id,client);
                viewClient.printCompany(client);
                viewClient.createOK();
                resul = true;
            }

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
    public void listPS(){
        viewClient.printAll(cc.values());
        viewClient.listOK();
    }

}
