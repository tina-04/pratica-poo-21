package upm.etsisi.Control;

import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.TicketCompany;
import upm.etsisi.View.ViewTicket;

public class PrinterTicketCompany implements IPrinter{
    private ViewTicket view;

    @Override
    public void print(Ticket ticket, String cashierId) {
        if(ticket!=null && ticket instanceof TicketCompany){
            if(ticket.getCashierId().equals(cashierId) ){
                view.printTicket(ticket);

            }
        }

    }
}
