package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

import java.util.List;

public class Client  {
    private String DNI;



    private String email;
    private String name;
    private List<Ticket> ticketList;

    public Client(String name, String email, String DNI) {
        this.name = name;
        this.email = email;
        this.DNI = DNI;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void setTicketList(List<Ticket> ticketList) {
        this.ticketList = ticketList;
    }
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }


}
