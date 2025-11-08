package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

import java.util.List;

public class Client extends User {
  private String name;
    private String email;
    private String DNI;


    private List<Ticket> ticketList;

    public Client(String name, String email, String DNI) {
        super(name, email, Rol.CLIENT);
        this.name = name;
        this.email = email;
        this.DNI = DNI;
    }
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }


    public String getEmailClient() {
        return email;
    }

    public void setEmailClient(String email) {
        this.email = email;
    }

    public String getNameClient() {
        return name;
    }

    public void setNameClient(String name) {
        this.name = name;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }





}
