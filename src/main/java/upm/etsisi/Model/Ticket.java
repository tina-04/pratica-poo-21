package upm.etsisi.Model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ticket {
    //private Map<Product, Integer> ticket;

    private List<Product> products;
    private double total;
    private double discount;
    private double finalPrice;

    public Ticket(List<Product> products) {
        this.products = products;
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
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

    public double getDiscount() {
        return discount;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}
