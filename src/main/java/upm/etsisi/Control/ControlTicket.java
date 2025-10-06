package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewProduct;

import java.util.ArrayList;
import java.util.List;

public class ControlTicket {
    private final int MAX_PRODUCT = 100;


    private Ticket ticket;

    public ControlTicket(Ticket ticket) {
        this.ticket = new Ticket();
    }

    public void newTicket() {
        this.ticket = new Ticket();
    }

    public void addProduct(Product product) {
        ArrayList<Product> products = ticket.getProducts();
        if (product != null && products.size() < MAX_PRODUCT) {
            products.add(product);
            printTicket();
        }
    }

    public void removeProduct(Product product) {
        ArrayList<Product> products = ticket.getProducts();
        products.remove(product);
        printTicket();
        System.out.println("ticket removed: ok");
    }

    /*private String printTicket() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Product product : products) {
            stringBuilder.append(ViewUtility.bracketLeft).append(ViewProduct.classProduct).append(ViewUtility.comma).
                    append(ViewProduct.id).append(ViewUtility.colon).append(product.getId()).append(ViewUtility.comma).
                    append(ViewProduct.name).append(ViewUtility.colon).append(product.getName()).append(ViewUtility.comma).
                    append(ViewProduct.category).append(ViewUtility.colon).append(product.getCategory()).append(ViewUtility.comma).
                    append(ViewProduct.price).append(ViewUtility.colon).append(product.getPrice()).
                    append(ViewUtility.bracketRight).append(ViewTicket.discount).append(calculateDiscount(product));
        }

        stringBuilder.append(ViewTicket.totalPrice).append(ticket.getTotal()).
                append(ViewTicket.totalDiscount).append(ticket.getDiscount()).
                append(ViewTicket.finalPrice).append(ticket.getFinalPrice()).
                append(ViewTicket.printed);

        return stringBuilder.toString();
    }*/

    public void printTicket() {
        ArrayList<Product> products = ticket.getProducts();
        for (Product product : products) {
            if(product != null){
                System.out.println(("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                        product.getCategory()+ "price : " + product.getPrice() + " **discount -" + calculateDiscount(product)));
            }
        }
        calculateTotal(products);

        System.out.println("Total price: " + ticket.getTotal());
        System.out.println("Total discount: " + ticket.getDiscount());
        System.out.println("Final Price: " + ticket.getFinalPrice());
    }

    private void calculateTotal(ArrayList<Product> products) {
        double total = 0.0;
        double discount = 0.0;

        for (Product product : products) {
            total += product.getPrice();
            discount = calculateDiscount(product);
        }

        ticket.setDiscount(discount);
        ticket.setTotal(total);

        ticket.setFinalPrice(total-discount);
    }

    public double calculateDiscount(Product product) {

        double discount = 0.0;
        Category category = product.getCategory();
        switch (category) {
            case MERCH:
                discount += 0 * product.getPrice();
                break;
            case STATIONERY:
                discount += 0.05 * product.getPrice();
                break;
            case CLOTHES:
                discount += 0.07 * product.getPrice();
                break;
            case BOOK:
                discount += 0.10 * product.getPrice();
                break;
            case ELECTRONICS:
                discount += 0.03 * product.getPrice();
                break;
        }
        return discount;
    }
}
