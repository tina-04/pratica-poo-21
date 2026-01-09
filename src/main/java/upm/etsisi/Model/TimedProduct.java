package upm.etsisi.Model;

import upm.etsisi.Utility.ProductType;
import java.time.LocalDate;

public class TimedProduct extends Product {
    private LocalDate expiration;
    private int maxPersonal;
    private ProductType productType;
    private int actualPeople;

    public TimedProduct(String id, String name, double price, LocalDate expiration, int maxPersonal, ProductType productType) {
        super(id, name, price);
        this.expiration = expiration;
        this.maxPersonal = maxPersonal;
        this.productType = productType;
        this.actualPeople = 0;
    }

    @Override
    public double calculatePrice(int quantity) {
        return price * quantity;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }

    public int getMaxPersonal() {
        return maxPersonal;
    }

    public void setMaxPersonal(int maxPersonal) {
        this.maxPersonal = maxPersonal;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public int getActualPeople() {
        return actualPeople;
    }

    public void setActualPeople(int actualPeople) {
        this.actualPeople = actualPeople;
    }
}