package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewProduct;
import upm.etsisi.View.ViewTicket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControlTicket {
    private final int MAX_PRODUCT = 100;

    private Ticket ticket;
    private HashMap<Category, Integer> categoryCounter = new HashMap<>();
    private ViewTicket viewTicket;

    public ControlTicket() {
        this.ticket = new Ticket();
        this.viewTicket = new ViewTicket();

    }


    public void newTicket() {
        this.ticket = new Ticket();
        this.categoryCounter = new HashMap<>();
    }


    public void addProduct(Product product, int amount) {
        List<Product> products = ticket.getProducts();
        if (product != null && products.size() < MAX_PRODUCT) {
            for (int i = 1; i <= amount; i++) {
                products.add(product);
                Category cat = product.getCategory();
                int count = categoryCounter.getOrDefault(cat, 0);
                categoryCounter.put(cat, count + 1);
            }
        }
        printTicket();
    }

    public void removeProduct(Product product) {
        List<Product> products = ticket.getProducts();
        int eliminated = 0;
        while (products.remove(product)) {
            eliminated++;
        }

        Category cat = product.getCategory();
        int count = categoryCounter.getOrDefault(cat, 0);
        if (eliminated > 0) {
            if (count > eliminated) {
                categoryCounter.put(cat, count - eliminated);
            } else {
                categoryCounter.remove(cat);
            }
        }

        // Este creo que está mal, no piden todos los restantes, sino lo contrario solo el producto borrado, revisar en pruebas

    }

    public boolean removeProduct1() {
        boolean result = false;
        List<Product> productList = ticket.getProducts();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                productList.remove(productList.get(i));
                result = true;
                printTicket();

            }
        }

        return result;
    }


    public void printTicket() {
        List<Product> products = ticket.getProducts();

         double total = 0;
        double totalDiscount = 0;


        for (Product product : products) {
            if (product != null) {
                Category cat = product.getCategory();
                boolean hasDiscount = categoryCounter.getOrDefault(cat, 0) >= 2;
                double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                total += product.getPrice();
                totalDiscount += discount;

                if (hasDiscount) {
                    double discountProduct = calculateDiscount(product);
                    viewTicket.printProductDiscount(product, discountProduct);
                } else {

                    viewTicket.printProduct(product);
                }

            }
        }
        //calculateTotal(products);
        ticket.setTotal(total);
        ticket.setDiscount(totalDiscount);
        ticket.setFinalPrice(total - totalDiscount);


        viewTicket.prices(ticket);


    }

    public void calculateTotal(List<Product> products) {
        double total = 0.0;
        double discount = 0.0;

        for (Product product : products) {
            total += product.getPrice();
            discount = calculateDiscount(product);
        }

        ticket.setDiscount(discount);
        ticket.setTotal(total);
        ticket.setFinalPrice(total - discount);

        viewTicket.prices(ticket);
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
