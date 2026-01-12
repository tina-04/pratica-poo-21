package upm.etsisi.Model;

public class TicketCompany extends Ticket{

    private String type;

    public TicketCompany(String id, String cashierId, String clientId, String type) {
        super(id, cashierId, clientId);
        this.type=type;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


}
