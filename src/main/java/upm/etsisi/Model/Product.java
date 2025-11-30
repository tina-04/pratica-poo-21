package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;

import java.time.LocalDate;


public class Product {
    private Integer id;
    private Category category;
    private String name;
    private double price;

    private LocalDate expiration;
    private int maxPersonal;
    private String personalizationList;
    private ProductType productType;



    private int acutalPeople;


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

    public Product(Integer id, String name,  Category category , double price, LocalDate expiration ,Integer maxPersonal, String personalizationList ) {
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
        this.expiration = expiration;
        this.productType=null;
        this.acutalPeople = 0;
    }
    public void setMaxPersonal(int maxPersonal) {
        this.maxPersonal = maxPersonal;
    }

    public int getActualPeople() {
        return acutalPeople;
    }

    public void setActualPeople(int acutalPeople) {
        this.acutalPeople = acutalPeople;
    }
    public String getPersonalizationList() {
        return personalizationList;
    }

    public void setPersonalizationList(String list) {
        this.personalizationList = list;
    }
    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate date) {
        this.expiration = date;
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
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getMaxPersonal() {
        return maxPersonal;
    }

    public void setMaxPersonal(Integer maxPersonal) {
        this.maxPersonal = maxPersonal;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType type) {
        this.productType = type;
    }

}
