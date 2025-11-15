package upm.etsisi.Model;

import upm.etsisi.Utility.Category;


public class Product {
    private int id;
    private Category category;
    private String name;
    private double price;
    private String textPersonal;

    public Product(int id, String name, Category category, double price) {
        this.id = id;
        if(name.length() < 100 && !name.isEmpty()){
            this.name = name;
        }else{
            this.name = null;
        }
        this.category = category;
        if(price > 0){
            this.price = price;
        }
    }
    public Product(String id, String name, double price, String max_people) {
        this.id=Integer.valueOf(id);
        this.name=name;
        this.price=price;
        this.textPersonal=max_people;
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
