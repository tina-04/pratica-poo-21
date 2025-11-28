package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Status;
import upm.etsisi.View.ViewTicket;

import java.time.LocalDateTime;
import java.util.*;

public class ControlTicket {
    private final int MAX_PRODUCT = 100;

    private ArrayList<Ticket> ticketList;

    private HashMap<Category, Integer> categoryCounter = new HashMap<>();
    private ViewTicket viewTicket;
    private static ControlTicket instance;

    public static ControlTicket getInstance() {
        if (instance == null) {
            instance = new ControlTicket(null,null);
        }
        return instance;
    }
    private ControlTicket(String cashierId, String userId) {
        this.ticketList = new ArrayList<>();
        this.viewTicket = new ViewTicket();
    }

    public void newTicket(String cashierId, String userId) {
        this.ticketList = new ArrayList<>();
        this.categoryCounter = new HashMap<>(); //TODO
    }
    public void newTicket(String id, String cashierId, String userId){
        //TODO
    }


    public boolean existTikcet(String id) {
        boolean exist = false;
        for(int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i) != null) {
                if (ticketList.get(i).getId() == id  ){
                    exist = true;
                }
            }
        }

        return exist;
    }
    public Ticket searchTicket(String id) {
        Ticket ticket = null;
        for (int i = 0; i < ticketList.size(); i++) {
            if (ticketList.get(i).getId() == id) {
                ticket = ticketList.get(i);
            }
        }
        return ticket;
    }




    /*
    public void addProduct(Product product, int amount) {//TODO
        List<Product> products = ticket.getProducts();
        if (product != null && products.size() < MAX_PRODUCT) {
            for (int i = 1; i <= amount; i++) {
                if(products.size() < MAX_PRODUCT){
                    products.add(product);
                    Category category = product.getCategory();
                    int count = categoryCounter.getOrDefault(category, 0);
                    categoryCounter.put(category, count + 1);

                }

            }
        }
        Collections.reverse(products);
        //printTicket();
        Collections.reverse(products);
        viewTicket.createOK();
    }

     */
    public void add(String ticketId, String cashierId, String productId, String amount, String[] personalizations) {
        // Mi sugerencia es que el comando mande el String[] tanto si hay como si no, y si está vacio simplemente no procesarlo
    }

    public void removeProduct(String ticketId, String cashierId, String productId) {
        Product product = ControlProduct.getInstance().searchProduct(Integer.parseInt(productId));
        Ticket ticket = searchTicket(ticketId);
        if(product!=null){
            List<Product> products = ticket.getProducts();
            int eliminated = 0;
            while (products.remove(product)) {
                eliminated++;
            }

            Category category = product.getCategory();
            int count = categoryCounter.getOrDefault(category, 0);
            if (eliminated > 0) {
                if (count > eliminated) {
                    categoryCounter.put(category, count - eliminated);
                    viewTicket.removeOK();
                } else {
                    categoryCounter.put(category, 0);
                }
            }
        }

    }


    public void printTicket(String ticketId, String cashId) {

        Ticket ticket = searchTicket(ticketId);
        List<Product> products = ticket.getProducts();
        Map<Category, Integer> categoryCounterACT = ticket.getCategoryCounter();
        // Getter del mapa que se va a crear en cada Ticket y se hace con eso igual

        if (ticket.getStatus() != Status.CERRADO) {
            StringBuilder s1 = new StringBuilder();
            s1.append(ticket.getId()).append("-").append(LocalDateTime.now());
            ticket.setId(s1.toString());
            ticket.setStatus(Status.CERRADO);
        }

        double total = 0;
        double totalDiscount = 0;

        for (Product product : products) {
            if (product != null) {
                Category category = product.getCategory();
                boolean hasDiscount = categoryCounterACT.getOrDefault(category, 0) >= 2;
                // Aquí va a tener que dejar de usar el categoryCounter de dentro de este Controlador
                // y usar el de dentro del Ticket que estés imprimiendo
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
        viewTicket.printOK();
    }

    public void ticketList(){
        viewTicket.ticketList(ticketList);
        viewTicket.listOK();
    }

    public double calculateDiscount(Product product) {

        double discount = 0.0;
        Category category = product.getCategory();
        switch (category) {
            case MERCH:
                discount = 0 * product.getPrice();
                break;
            case STATIONERY:
                discount = 0.05 * product.getPrice();
                break;
            case CLOTHES:
                discount = 0.07 * product.getPrice();
                break;
            case BOOK:
                discount = 0.10 * product.getPrice();
                break;
            case ELECTRONICS:
                discount = 0.03 * product.getPrice();
                break;
        }
        return discount;
    }

    public void calculateTotal(List<Product> products) {
        double total = 0.0;
        double discount = 0.0;

        for (Product product : products) {
            total += product.getPrice();
            discount = calculateDiscount(product);
        }

        //ticket.setDiscount(discount);
        //ticket.setTotal(total);
        //ticket.setFinalPrice(total - discount);

        //viewTicket.prices(ticket);
    }
}
