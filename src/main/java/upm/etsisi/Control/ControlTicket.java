package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;

import java.util.Map;

public class ControlTicket {
    //private List<Product> products;
    //private final int MAX_PRODUCT = 100;

    private Ticket ticket;

    public ControlTicket(Ticket ticket) {
        this.ticket = new Ticket();
    }

    public void newTicket() {
        this.ticket = new Ticket();
        System.out.println("ticket new: ok");
    }

    public void addProduct(Product product, int amount) {
        Map<Product, Integer> items = ticket.getTicket();
        items.put(product, amount);
        calculateTotal();
        printTicket();
        System.out.println("ticket added: ok");
    }

    public void removeProduct(Product product) {
        Map<Product, Integer> items = ticket.getTicket();
        items.remove(product);
        calculateTotal();
        printTicket();
        System.out.println("ticket removed: ok");
    }

    private void printTicket() {
        Map<Product, Integer> items = ticket.getTicket();

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            //////   product.toString();
        }

        System.out.println("Total price: " + ticket.getTotal());
        System.out.println("Total discount: " + ticket.getDiscount());
        System.out.println("Final Price: " + ticket.getFinalPrice());
        System.out.println("ticket print: ok");

    }

    private void calculateTotal() {
        Map<Product, Integer> items = ticket.getTicket();
        double total = 0.0;
        double discount = 0.0;

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product=  entry.getKey();
            int amount = entry.getValue();
            total += amount * product.getPrice();

            String category = product.getCategory();
            switch (category) {
                case "MERCH":
                    discount += amount * 1 * product.getPrice();
                    break;
                case "PAPELERIA":
                    discount += amount * 0.05 * product.getPrice();
                    break;
                case "ROPA":
                    discount += amount * 0.07 * product.getPrice();
                    break;
                case "LIBRO":
                    discount += amount * 0.10 * product.getPrice();
                    break;
                case "ELECTRONICA":
                    discount += amount * 0.03 * product.getPrice();
                    break;
            }
        }
        ticket.setDiscount(discount);
        ticket.setTotal(total);

        ticket.setFinalPrice(total-discount);
    }
}
