package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewProduct;

public class Product {


    private int id;
    private Category category;
    private String name;
    private double price;
    private ViewProduct viewProduct;

    public Product(int id, String name, Category category, double price) {
        this.id = id;
        if(name.length() < 100 && !name.isEmpty()){
            this.name = name;
        }
        this.category = category;
        if(price > 0){
            this.price = price;
        }
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

    public String toString(Product product) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(ViewProduct.id).append(ViewProduct.colon).append(product.getId()).append(ViewProduct.comma).
                append(ViewProduct.name).append(ViewProduct.colon).append(product.getName()).append(ViewProduct.comma).
                append(ViewProduct.category).append(ViewProduct.colon).append(product.getCategory()).append(ViewProduct.comma).
                append(ViewProduct.price).append(ViewProduct.colon).append(product.getPrice());
        return stringBuilder.toString();
    }
    public String toString1(Product product){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(viewProduct.printPoroducto(product));
        return stringBuilder.toString();

    }
}
