package upm.etsisi.Model;

public abstract class Product {
    protected Integer id;
    protected String name;
    protected double price;

    public Product(Integer id, String name, double price) {
        this.id = id;
        if (name != null && name.length() < 100 && !name.isEmpty()) {
            this.name = name;
        } else {
            this.name = null;
        }
        if (price > 0) {
            this.price = price;
        }
    }

    public abstract double calculatePrice(int quantity);

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name != null && name.length() < 100 && !name.isEmpty()) {
            this.name = name;
        }
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }
}