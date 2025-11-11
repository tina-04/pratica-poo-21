package upm.etsisi.Model;

import upm.etsisi.Utility.Rol;

import java.util.List;

public class Client extends User {
    private String DNI;
    private List<Ticket> ticketList;

    public Client(String name, String email, String DNI) {
        super(name, email);
        this.DNI = DNI;
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
