package upm.etsisi.Model;

import upm.etsisi.Utility.Category;

public class Product {


    private String id;
    private int numPositive;
    private Category category;
    private String name;
    private int price;

    public Product(String id, int numPositive, Category category, String name, int price) {
        this.id = id;
        this.numPositive = numPositive;
        this.category = category;
        this.name = name;
        this.price = price;
    }

    public int getNumPositive() {
        return numPositive;
    }

    public void setNumPositive(int numPositive) {
        this.numPositive = numPositive;
    }


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
