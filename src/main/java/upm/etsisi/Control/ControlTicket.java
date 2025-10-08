package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewProduct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ControlTicket {
    private final int MAX_PRODUCT = 100;


    private Ticket ticket;
    private HashMap<Category, Integer> categoryCounter = new HashMap<>();

    public ControlTicket() {
        this.ticket = new Ticket();
    }

    public void newTicket() {
        this.ticket = new Ticket();
        this.categoryCounter = new HashMap<>();
    }

    public void addProduct(Product product) {
        ArrayList<Product> products = ticket.getProducts();
        if (product != null && products.size() < MAX_PRODUCT) {
            products.add(product);

            Category cat = product.getCategory();
            int count = categoryCounter.getOrDefault(cat, 0);
            categoryCounter.put(cat, count + 1);

            printTicket();
        }
    }

    public void removeProduct(Product product) {
        ArrayList<Product> products = ticket.getProducts();
        int eliminated = 0;
        while(products.remove(product)){
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

        //printTicket(); Este creo que está mal, no piden todos los restantes, sino lo contrario solo el producto borrado, revisar en pruebas
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

        double total = 0;
        double totalDiscount = 0;

        for (Product product : products) {
            if(product != null){
                Category cat = product.getCategory();
                boolean hasDiscount = categoryCounter.getOrDefault(cat, 0) >= 2;
                double discount = hasDiscount ? calculateDiscount(product) : 0.0;
                total += product.getPrice();
                totalDiscount += discount;

                if(hasDiscount){
                    System.out.println("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                            product.getCategory()+ "price : " + product.getPrice() + " **discount -" + calculateDiscount(product));
                }
                else{
                    System.out.println("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                            product.getCategory()+ "price : " + product.getPrice());
                }

            }
        }
        //calculateTotal(products); De momento lo oculto
        ticket.setTotal(total);
        ticket.setDiscount(totalDiscount);
        ticket.setFinalPrice(total - totalDiscount);

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
