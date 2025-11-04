package upm.etsisi.Model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket {

    private List<Product> products;
    private double total;
    private double discount;
    private double finalPrice;
    private String cashierId;
    private String userId;



    private Client client;
    private Cashier cashier;

    public Ticket(String cashierId, String userId) {
        this.products = new ArrayList<Product>();
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
        this.cashierId = cashierId;
        this.userId = userId;
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

    public void setUserId(String userId) {this.userId = userId;}

    public String getUserId() {return userId;}

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
}
