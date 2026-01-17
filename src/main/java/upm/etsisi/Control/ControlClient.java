package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewClient;
import upm.etsisi.View.ViewTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.*;


public class ControlClient {
    private List<Client> clientsList;
    private Map<String, ClientAndCompany> cc = new HashMap<>();
    private List<Ticket> ticketList;
    private ViewClient viewClient;
    private static ControlClient instance;

    private static final String RUTA = "src/main/java/upm/etsisi/Persistence/Clients.csv";

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
        loadClients();
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

    public void saveClients(){
        File file = new File(RUTA);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                for (ClientAndCompany item : cc.values()) { // No tengo ni idea de los datos guardados
                                                            // pero eso creo que tiene todos los clientes
                    StringBuilder sb = new StringBuilder();

                    if (item instanceof Client) {
                        Client c = (Client) item;
                        sb.append("CLIENT").append(";")
                                .append(c.getDNI()).append(";")
                                .append(c.getName()).append(";")
                                .append(c.getEmail()).append(";")
                                .append(c.getCashierId());

                    } else if (item instanceof ClientCompany) { // TODO revisar con lo nuevo
                        ClientCompany cp = (ClientCompany) item;
                        sb.append("COMPANY").append(";")
                                .append(cp.getId()).append(";")
                                .append(cp.getName()).append(";")
                                .append(cp.getEmail()).append(";")
                                .append(cp.getCashierId());
                    }

                    writer.write(sb.toString());
                    writer.newLine();
                }
                System.out.println("Datos guardados en: " + RUTA);
            }
        } catch (IOException ignored) {}
    }

    public void loadClients(){
        File file = new File(RUTA);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            cc.clear();
            clientsList.clear();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");
                String type = data[0];
                try {
                    String id = data[1];
                    String name = data[2];
                    String email = data[3];
                    String cashierId = data[4];

                    switch (type) {
                        case "CLIENT":
                            Client c = new Client(name, id, email, cashierId);
                            cc.put(c.getDNI(), c);
                            clientsList.add(c);
                            break;

                        case "COMPANY":
                            ClientCompany cp = new ClientCompany(name, id, email, cashierId);
                            cc.put(cp.getId(), cp);
                            break;
                    }
                } catch (Exception ignored) {}
            }
        } catch (IOException ignored) {}
    }
}
