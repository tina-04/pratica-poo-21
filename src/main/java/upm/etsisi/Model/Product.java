package upm.etsisi.Model;

import upm.etsisi.Utility.Category;

public class Product {


    private int id;
    private int numPositive;
    private Category category;
    private String name;
    private double price;

    public Product(int id, int numPositive, Category category, String name, double price) {
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

    public void setNumPositive(int num) {
        this.numPositive = num;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
