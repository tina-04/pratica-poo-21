package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Model.Ticket;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Status;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewTicket;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ControlTicket {

    private ArrayList<Ticket> ticketList;

    private ViewTicket viewTicket;
    private static ControlTicket instance;

    public static ControlTicket getInstance() {
        if (instance == null) {
            instance = new ControlTicket();
        }
        return instance;
    }
    private ControlTicket() {
        this.ticketList = new ArrayList<>();
        this.viewTicket = new ViewTicket();
    }


    public boolean newTicket(String id, String cashierId, String userId){
        boolean resul =false;
       if(!existTikcet(id)){
           Ticket ticket = new Ticket(id, cashierId,userId);
           viewTicket.printTicket(ticket);
           viewTicket.prices(ticket);
           ticketList.add(ticket);
           viewTicket.newOk();
           ControlCashier.getInstance().addTicket(cashierId, ticket);
           ControlClient.getInstance().addTicket(userId,ticket);
           resul=true;
       }


        return resul;
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
            if (ticketList.get(i).getId().equals(id)) {
                ticket = ticketList.get(i);
            }
        }
        return ticket;
    }


    public void add(String ticketId, String cashierId, String productId, String amount, String[] personalizations) {
        Ticket ticket = searchTicket(ticketId);
        Product product = ControlProduct.getInstance().searchProduct(Integer.parseInt(productId));
        if(cashierId == ticket.getCashierId()) return;
        int amountInt = Integer.parseInt(amount);

        if (ticket.getStatus() == Status.EMPTY) {
            ticket.setStatus(Status.OPEN);
        }
        if(product.getProductType() == ProductType.BASIC){
            if (personalizations != null && personalizations.length > 0) {
                String joinedPers = String.join(",", personalizations);
                product = new Product(product.getId(), product.getName(), product.getCategory(), product.getPrice(),product.getExpiration(), personalizations.length, joinedPers);
            }

            for (int i = 0; i < amountInt; i++) {
                if (ticket.getProducts().size() < 100){
                    ticket.getProducts().add(product);
                    ticket.setCategoryCounter(product.getCategory(), 1);
                }
            }
        } else {
            for (Product p : ticket.getProducts()) {
                if (p.getId() == (product.getId())) {
                    return;
                }
            }
            if (ticket.getProducts().size() < 100 || amountInt <= 0 || amountInt > product.getMaxPersonal()){
                return;
            }
            Product productToAdd = new Product(product.getId(), product.getName(), null,product.getPrice(), product.getExpiration(), amountInt,null);
            productToAdd.setProductType(product.getProductType());
            ticket.getProducts().add(productToAdd);
        }
        viewTicket.createOK();
    }

    public void removeTicker(List<Ticket> ticket){
        for(int i=0; i<ticket.size(); i++){
            if(existTikcet(ticket.get(i).getId())){
                ticketList.remove(ticket.get(i));

            }
        }
    }

    public void removeProduct(String ticketId, String cashierId, String productId) {
        // Lo del cashierId lo mismo que el al añadirlo

        Ticket ticket = searchTicket(ticketId);
        if(cashierId == ticket.getCashierId()) return;
        int id = Integer.parseInt(productId);
        List<Product> products = ticket.getProducts();

        Product toRemove = null;
        for (Product p : products) {
            if (p.getId() == id && p.getProductType() != ProductType.BASIC) {
                products.remove(toRemove);
                Category cat = toRemove.getCategory();
                ticket.setCategoryCounter(cat, -1);
            }
            else if (p.getId() == id && p.getProductType() == ProductType.BASIC){
                products.remove(toRemove);
            }
        }

        if (products.isEmpty()) {
            ticket.setStatus(Status.EMPTY);
        }

        viewTicket.removeOK();

    }


    public void printTicket(String ticketId, String cashierId) {
        Ticket ticket = searchTicket(ticketId);
        if(cashierId == ticket.getCashierId()) return;
        if (ticket.getStatus() == Status.OPEN || ticket.getStatus() == Status.EMPTY) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd-HH:mm-");
            String date = LocalDateTime.now().format(formatter);
            ticket.setId(ticket.getId() + "-" + date);
            ticket.setStatus(Status.CLOSE);
        }

        viewTicket.printTicket(ticket);

        List<Product> products = ticket.getProducts();
        products.sort(Comparator.comparing(p -> p.getName().toLowerCase()));

        double total = 0.0;
        double totalDiscount = 0.0;

        for (Product product : products) {
            if (product == null) continue;

            int categoryCount = ticket.getCategoryCount(product.getCategory());
            boolean hasDiscount = categoryCount >= 2;
            double discount = hasDiscount ? calculateDiscount(product) : 0.0;
            double price = product.getPrice();

            total += price;
            totalDiscount += discount;

            if (product.getPersonalizationList() != null && !product.getPersonalizationList().isEmpty()) {
                if (hasDiscount) {
                    viewTicket.printProductDiscountPersonlization(product, discount);
                } else {
                    viewTicket.printProductDiscountPersonlization(product, 0.0);
                }
            } else {
                if (hasDiscount) {
                    viewTicket.printProductDiscount(product, discount);
                } else {
                    viewTicket.printProduct(product);
                }
            }
        }

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


}
