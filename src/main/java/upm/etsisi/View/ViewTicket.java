package upm.etsisi.View;

import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.BasicProduct;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Model.TimedProduct;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;

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
    public void printProductBasic(Product product) {
        if(product instanceof BasicProduct){
            BasicProduct basicProd = (BasicProduct) product;

            messageOutput("{class:Product, id:" + basicProd.getId()+ ", name:'" + basicProd.getName()+
                    "', category:" + basicProd.getCategory()+ ", price:" + basicProd.getPrice() + "}");
        }

    }
    public void printProductDiscount(Product product, double discount) { // Locale.US imprime . en vez de , para los decimales
        BasicProduct basicProd = (BasicProduct) product;

        messageOutput("{class:Product, id:" + basicProd.getId() + ", name:'" + basicProd.getName() + "', category:" +
                basicProd.getCategory() + ", price:" + String.format(Locale.US, "%.2f", basicProd.getPrice()) + "} **discount -" + String.format(Locale.US, "%.2f", discount));
    }
    public void printProductDiscountPersonlization(Product product, double discount) {
        BasicProduct basicProd = (BasicProduct) product;

        messageOutput("{class:ProductPersonalized, id:" + basicProd.getId() + ", name:'" + basicProd.getName() + "', category:" +
                basicProd.getCategory() + ", price:" + String.format(Locale.US,"%.2f", basicProd.getPrice()) +  "， personalizationList:[" + basicProd.getPersonalizationList()+"]} **discount -" + String.format(Locale.US, "%.2f", discount));
    }
    public void printProductPersonalization(Product product) {
        if (product instanceof BasicProduct) {
            BasicProduct basicProduct = (BasicProduct) product;

            if (basicProduct.getPersonalizationList() == null) {
                messageOutput("{class:ProductPersonalized, id:" + basicProduct.getId() +
                        ", name:'" + basicProduct.getName() +
                        "', category:" + basicProduct.getCategory() +
                        ", price:" + String.format(Locale.US, "%.2f", basicProduct.getPrice()) +
                        "}");
            } else {
                messageOutput("{class:ProductPersonalized, id:" + basicProduct.getId() +
                        ", name:'" + basicProduct.getName() +
                        "', category:" + basicProduct.getCategory() +
                        ", price:" + String.format(Locale.US, "%.2f", basicProduct.getPrice()) +
                        ", personalizationList:[" + basicProduct.getPersonalizationList() + "]" +
                        "}");
            }
        }
    }
    public void printProductFood(Product product, int actualPeople) {
        if(product instanceof TimedProduct){
            TimedProduct timedProduct = (TimedProduct) product;
            messageOutput("{class:Food, id:" + timedProduct.getId()+ ", name: '" + timedProduct.getName()+
                    ", price:" + timedProduct.getPrice() +  ", date of Event:"+timedProduct.getExpiration()+
                    ",max people allowed:"+timedProduct.getMaxPersonal()+",actual people in event;"+ actualPeople+"}");
        }

    }
    public void printProductMeeting(Product product, int actualPeople) {
        if(product instanceof TimedProduct){
            TimedProduct timedProduct = (TimedProduct) product;
            messageOutput("{class:Meeting, id:" + timedProduct.getId()+ ", name: '" + timedProduct.getName()+
                    "', price:" + timedProduct.getPrice() +  ", date of Event:"+timedProduct.getExpiration()+
                    ",max people allowed:"+timedProduct.getMaxPersonal()+ ", actual people in event:" + actualPeople+"}");
        }
    }

    public void ticketList(ArrayList<Ticket> ticketList) {
        messageOutput("Ticket List:");
        for(Ticket ticket : ticketList){
            messageOutput(ticket.getId() + " - " + ticket.getStatus());
        }
    }

    public void prices(Ticket  ticket) {
        messageOutput("Total price: " + ticket.getTotal());
        messageOutput("Total discount: " + String.format(Locale.US, "%.2f", ticket.getDiscount()));
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
