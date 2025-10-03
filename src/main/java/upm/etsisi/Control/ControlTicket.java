package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.View.ViewProduct;
import upm.etsisi.View.ViewTicket;
import upm.etsisi.View.ViewUtility;

import java.util.List;
import java.util.Map;

public class ControlTicket {
    private List<Product> products;
    private final int MAX_PRODUCT = 100;

    private Ticket ticket;

    public ControlTicket(Ticket ticket) {
        this.ticket = new Ticket(products);
    }

    public void newTicket() {
        this.ticket = new Ticket(products);
        System.out.println("ticket new: ok");
    }

    public void addProduct(Product product) {
        if (product != null) {}
        products.add(product);
        calculateTotal();
        printTicket();
        System.out.println("ticket added: ok");
    }

    public void removeProduct(Product product) {
        products.remove(product);
        calculateTotal();
        printTicket();
        System.out.println("ticket removed: ok");
    }

    private String printTicket() {
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
    }

    private void calculateTotal() {
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
        String category = product.getCategory();
        switch (category) {
            case "MERCH":
                discount += 0 * product.getPrice();
                break;
            case "PAPELERIA":
                discount += 0.05 * product.getPrice();
                break;
            case "ROPA":
                discount += 0.07 * product.getPrice();
                break;
            case "LIBRO":
                discount += 0.10 * product.getPrice();
                break;
            case "ELECTRONICA":
                discount += 0.03 * product.getPrice();
                break;
        }
        return discount;
    }
}
