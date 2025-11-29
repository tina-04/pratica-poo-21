package upm.etsisi.View;

import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewTicket  implements View{
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printTicket(Ticket ticket){
        messageOutput("Ticket:" + ticket.getId());
    }
    public void printProduct(Product product) {
        if(product != null){
            messageOutput("{class:Product, id:" + product.getId()+ ", name:'" + product.getName()+
                    "', category:" + product.getCategory()+ ", price:" + product.getPrice() + "}");
        }

    }
    public void printProductDiscount(Product product, double discount) { // Locale.US imprime . en vez de , para los decimales

        messageOutput("{class:Product, id:" + product.getId() + ", name:'" + product.getName() + "', category:" +
                product.getCategory() + ", price:" + String.format(Locale.US, "%.2f", product.getPrice()) + "} **discount -" + discount);
    }
    public void printProductDiscountPersonlization(Product product, double discount) {

        messageOutput("{class:ProductPersonalized, id:" + product.getId() + ", name:'" + product.getName() + "', category:" +
                product.getCategory() + ", price:" + String.format(Locale.US,"%.2f", product.getPrice()) +  "， personalizationList" + product.getPersonalizationList()+"} **discount -" + discount);
    }


    public void ticketList(ArrayList<Ticket> ticketList) {
        messageOutput("Ticket List:");
        for(Ticket ticket : ticketList){
            messageOutput(ticket.getId() + " - " + ticket.getStatus());
        }
    }

    public void prices(Ticket  ticket) {
        messageOutput("Total price: " + ticket.getTotal());
        messageOutput("Total discount: " + ticket.getDiscount());
        messageOutput("Final Price: " + ticket.getFinalPrice());
    }

    public void newOk(){messageOutput("ticket new: ok");}
    public void createOK(){
        messageOutput("ticket add: ok");
    }
    public void removeOK(){
        messageOutput("ticket remove: ok");
    }
    public void listOK(){
        messageOutput("ticket list: ok");
    }

    public void printOK(){
        messageOutput("ticket print: ok");
    }

}
