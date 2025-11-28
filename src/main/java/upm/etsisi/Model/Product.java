package upm.etsisi.Model;

import upm.etsisi.Utility.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;


public class Product {
    private int id;
    private Category category;
    private String name;
    private double price;

    private LocalDate expiration;
    private int maxPersonal;



    private String personalizationList;





    public Product(Integer id, String name, Category category, double price) {
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
    public Product(Integer id, String name, double price, LocalDate expiration , int maxPersonal) {
        this.id = id;
        if(name.length() < 100 && !name.isEmpty()){
            this.name = name;
        }else{
            this.name = null;
        }
        if(price > 0){
            this.price = price;
        }
        this.expiration = expiration;
        this.maxPersonal = maxPersonal;
    }
    public Product(Integer id, String name,  Category category , double price, int maxPersonal, String personalizationList ) {
        this.id = id;
        if(name.length() < 100 && !name.isEmpty()){
            this.name = name;
        }else{
            this.name = null;
        }
        if(price > 0){
            this.price = price;
        }
        this.category =category;
        this.maxPersonal = maxPersonal;
        this.personalizationList=personalizationList;
    }

    public Product(Integer id, String name,Category category ,double price, int maxPersonal) {
        this.id=Integer.valueOf(id);
        this.name=name;
        this.price=price;
        this.category = category;
        this.maxPersonal=maxPersonal;
    }
    public String getPersonalizationList() {
        return personalizationList;
    }

    public void setPersonalizationList(String personalizationList) {
        this.personalizationList = personalizationList;
    }
    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
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
    public int getMaxPersonal() {
        return maxPersonal;
    }

    public void setMaxPersonal(int maxPersonal) {
        this.maxPersonal = maxPersonal;
    }


}
