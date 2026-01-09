package upm.etsisi.Model;

import upm.etsisi.Utility.Category;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ProductService implements ProductsAndService {
    private String id;
    private Category category;

    private LocalDate expiration;
    public ProductService(Category category, LocalDate expiration){
        this.category=category;
        this.expiration=expiration;
    }
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDate getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDate expiration) {
        this.expiration = expiration;
    }
}
