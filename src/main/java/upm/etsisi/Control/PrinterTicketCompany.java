package upm.etsisi.Control;

import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.TicketCompany;
import upm.etsisi.View.ViewTicket;

public class PrinterTicketCompany implements IPrinter<TicketCompany>{
    private ViewTicket view;

    @Override
    public void print(TicketCompany ticket, String cashierId) {
        if(ticket != null){
            if(ticket.getCashierId().equals(cashierId) ){
                view.printTicket(ticket);
            }
        }

    }
}
