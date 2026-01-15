package upm.etsisi.Model;

import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;

public class BasicProduct extends Product {
    private Category category;
    private Integer maxPersonal;
    private String personalizationList;
    private ProductType productType;

    public BasicProduct(String id, String name, Category category, double price) {
        super(id, name, price);
        this.category = category;
        this.maxPersonal = null;
        this.personalizationList = null;
        this.productType = ProductType.BASIC;
    }

    public BasicProduct(String id, String name, Category category, double price, Integer maxPersonal, String personalizationList) {
        super(id, name, price);
        this.category = category;
        this.maxPersonal = maxPersonal;
        this.personalizationList = personalizationList;
        this.productType = ProductType.PERSONALIZATION;
    }

    @Override
    public double calculatePrice(int quantity) {
        return price * quantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getMaxPersonal() {
        return maxPersonal;
    }

    public void setMaxPersonal(Integer maxPersonal) {
        this.maxPersonal = maxPersonal;
    }

    public String getPersonalizationList() {
        return personalizationList;
    }

    public void setPersonalizationList(String personalizationList) {
        this.personalizationList = personalizationList;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}