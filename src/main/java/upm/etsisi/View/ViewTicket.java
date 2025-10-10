package upm.etsisi.View;

import upm.etsisi.Control.ControlTicket;
import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;

import java.util.List;

public class ViewTicket {
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printProduct(Product product) {

        if(product != null){
            messageOutput("{class:Product: id: " + product.getId()+ ", name : '" + product.getName()+
                    "', category : " + product.getCategory()+ ", price : " + product.getPrice() + "}");
        }

    }
    public void printProductDiscount(Product product, double discount) {

        messageOutput("{class:Product: id: " + product.getId() + ", name : '" + product.getName() + "', category : " +
                product.getCategory() + ", price : " + product.getPrice() + " **discount -" + discount);

    }

    public void totalPrice(Ticket  ticket) {
        messageOutput("Total price: " + ticket.getTotal());
    }
    public void discountPrice(Ticket  ticket) {
        messageOutput("Discount price: " + ticket.getDiscount());
    }
    public void finalPrice(Ticket  ticket) {
        messageOutput("Final price: " + ticket.getFinalPrice());
    }

}
