package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Status;
import upm.etsisi.Utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket {
    private List<Product> products;
    private HashMap<Category, Integer> categoryCounter = new HashMap<>();
    private double total;
    private double discount;
    private double finalPrice;

    private String id;
    private String cashierId;
    private String clientId;
    private Status  status;
    private Client client;
    private Cashier cashier;

    public Ticket(String cashierId, String clientId) {
        this.products = new ArrayList<Product>();
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
        this.cashierId = cashierId;
        this.clientId = clientId;
        this.status=Status.VACIO;

        StringBuilder s1 = new StringBuilder();
        s1.append(LocalDateTime.now().toString()).append("-").append(Utility.ticketId());
        this.id =s1.toString();
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {return products;}

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotal() {
        return total;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getDiscount() {return discount;}

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setCashierId(String cashierId) {this.cashierId = cashierId;}

    public String getCashierId() {return cashierId;}

    public void setClientId(String clientId) {this.clientId = clientId;}

    public String getClientId() {return clientId;}

    public Cashier getCashier() {
        return cashier;
    }

    public void setCashier(Cashier cashier) {
        this.cashier = cashier;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    public int getCategoryCount(Category category) { // Este da cuantos tiene esa categoria
        return categoryCounter.getOrDefault(category, 0);
    }

    public void setCategoryCounter(Category category, int variation) { // Este es para sumar o restarle a esa categoría
        categoryCounter.put(category, categoryCounter.getOrDefault(category, 0) + variation);
    }

    public Map<Category, Integer> getCategoryCounter() {
        return null;
    }
}
