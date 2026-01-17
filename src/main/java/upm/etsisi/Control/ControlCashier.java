package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewCashier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.io.*;


public class ControlCashier {

    private List<Cashier> cashierList;
    private List<Ticket> ticketList;
    private ViewCashier viewCashier;
    private static ControlCashier instance;

    private static final String RUTA = "src/main/java/upm/etsisi/Persistence/Cashiers.csv";

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
        loadCashiers();
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
    public boolean cashTicket(String id){
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

    public void saveCashiers() {
        File file = new File(RUTA);

        try {
            if (file.getParentFile() != null && !file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                // Apuntar línea por línea -----------------------------------
                for (Cashier cashier : cashierList) {
                    StringBuilder sb = new StringBuilder();

                    // Formato: ID;NOMBRE;EMAIL
                    sb.append(cashier.getCashierId()).append(";")
                            .append(cashier.getName()).append(";")
                            .append(cashier.getEmail());

                    writer.write(sb.toString());
                    writer.newLine();
                }
                // --------------------------------------------------------
                // Version final dependiendo de los atributos de cada uno
                System.out.println("Datos guardados en: " + RUTA);
            }
        } catch (IOException ignored) {

        }
    }


    public void loadCashiers(){
        File file = new File(RUTA);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            cashierList.clear();

            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;

                String[] data = line.split(";");

                try {
                    // Lógica idéntica al bloque de Product, pero directa
                    // data[0]=ID, data[1]=Name, data[2]=Email
                    if (data.length >= 3) {
                        Cashier cashier = new Cashier(data[0], data[1], data[2]);
                        cashierList.add(cashier);
                    }
                } catch (Exception ignored) {}
            }
        } catch (IOException e) {}
    }

}
