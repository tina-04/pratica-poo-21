package upm.etsisi.Model;

import java.util.HashMap;
import java.util.Map;

public class Ticket {
    private Map<Product, Integer> ticket;
    private double total;
    private double discount;
    private double finalPrice;

    public Ticket() {
        this.ticket = new HashMap<Product, Integer>();
        this.total = 0;
        this.discount = 0;
        this.finalPrice = 0;
    }

    public void setTicket(Map<Product, Integer> ticket) {
        this.ticket = ticket;
    }

    public Map<Product, Integer> getTicket() {
        return ticket;
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
