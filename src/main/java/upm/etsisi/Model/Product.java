package upm.etsisi.Model;

import upm.etsisi.Utility.Category;

public class Product {


    private String id;
    private int numPositive;
    private String category;
    private String name;
    private double price;

    public Product(String id, int numPositive, String category, String name, double price) {
        this.id = id;
        if(numPositive > 0){
            this.numPositive = numPositive;
        }
        if(name.length() < 100 && !name.isEmpty()){
            this.name = name;
        }
        this.category = category;
        if(price > 0){
            this.price = price;
        }

    }

    public int getNumPositive() {
        return numPositive;
    }

    public void setNumPositive(int numPositive) {
        this.numPositive = numPositive;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
