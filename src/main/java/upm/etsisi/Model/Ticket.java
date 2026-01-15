package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Status;
import upm.etsisi.Utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket<T extends ProductsAndService> {

    private Map<String, T> ps = new HashMap<>();
    private HashMap<Category, Integer> categoryCounter = new HashMap<>();
    private double total;
    private double discount;
    private double finalPrice;

    private String id;
    private String cashierId;
    private String clientId;
    private Status  status;


    public Ticket(String id, String cashierId, String clientId) {
        this.ps = new HashMap<>();
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
        this.cashierId = cashierId;
        this.clientId = clientId;
        this.status=Status.EMPTY;
        this.id =id;
    }
    public Map<String, T> getPs() {
        return ps;
    }

    /*public void setPs(Map<String, ProductsAndService> ps) {
        this.ps = ps;
    }*/

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

    public void setCashierId(String id) {this.cashierId = id;}

    public String getCashierId() {return cashierId;}

    public void setClientId(String id) {this.clientId = id;}


    public int getCategoryCount(Category category) { // Este da cuantos tiene esa categoria
        return categoryCounter.getOrDefault(category, 0);
    }

    public void setCategoryCounter(Category category, int variation) { // Este es para sumar o restarle a esa categoría
        categoryCounter.put(category, categoryCounter.getOrDefault(category, 0) + variation);
    }
}
