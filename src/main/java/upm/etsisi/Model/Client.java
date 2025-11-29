package upm.etsisi.Model;


import java.util.List;

public class Client  {
    private String DNI;
    private String email;
    private String name;
    private String cashierId;
    private List<Ticket> ticketList;

    public Client(String name, String DNI, String email, String cashierId) {
        this.name = name;
        this.DNI = DNI;
        this.email = email;
        this.cashierId = cashierId;
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

    public String getCashierId() {
        return cashierId;
    }
    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }
}
