package upm.etsisi.Control;

import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.TicketCompany;
import upm.etsisi.View.ViewTicket;

public class PrinterTicketCompany implements IPrinter{
    private final ViewTicket viewTicket = new ViewTicket();

    @Override
    public void print(Ticket ticket, String cashierId) {

        if (!(ticket instanceof TicketCompany)) {
            return;
        }

        if (!ticket.getCashierId().equals(cashierId)) {
            return;
        }

        // Impresión específica de tickets de empresa
        viewTicket.printTicket(ticket);
        viewTicket.prices(ticket);
    }
}
