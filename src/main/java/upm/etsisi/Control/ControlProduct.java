package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewProduct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.ChronoLocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private final int MAX_PEOPLE = 100;
    private final int MAX_TEXT = 100;
    private ViewProduct viewProduct;
    private static ControlProduct instance;

    public static ControlProduct getInstance() {
        if (instance == null) {
            instance = new ControlProduct();
        }
        return instance;
    }

    private ControlProduct() {
        this.productList = new ArrayList<>();
        this.numProducts = 0;
        this.viewProduct = new ViewProduct();
    }
    public boolean existProduct(int id) {
        boolean exist = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                if (productList.get(i).getId() == id) {
                    exist = true;
                }
            }
        }

        return exist;
    }

    public Product searchProduct(int id) {
        Product product = null;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                product = productList.get(i);
            }
        }
        return product;
    }

    public boolean addProduct2(Integer id, String name, Category category, double price, Integer max_people) {
        boolean result = false;
        if (max_people == null) {
            if (productList.size() < MAX_PRODUCT) {
                if (!existProduct(Integer.valueOf(id))) {
                    Product product = new Product(id, name, category, price);
                    product.setProductType(ProductType.BASIC);
                    productList.add(product);
                    numProducts++;
                    result = true;
                    viewProduct.printProduct(product);
                    viewProduct.createOK();
                }
            }
        } else {
            if (productList.size() < MAX_PRODUCT) {
                if (!existProduct(Integer.valueOf(id))) {
                    Product product = new Product(id, name, category, price, max_people,null);
                    product.setProductType(ProductType.BASIC);
                    productList.add(product);
                    numProducts++;
                    result = true;
                    viewProduct.printProductP(product);
                    viewProduct.createOK();
                }
            }

        }


        return result;
    }

    public boolean addFood(Integer id, String name, double price, LocalDate expiration, int max_people) {
        boolean resul = false;
        LocalDate now = LocalDate.now();
        LocalDate twelve =now.plusDays(3);
        if (expiration.isBefore(twelve)) {
            if (max_people <= MAX_PEOPLE) {
                double newPrice = max_people*price;
                Product product = new Product(id, name, newPrice, expiration, max_people);
                product.setProductType(ProductType.FOOD);
                viewProduct.printProductFood(product);
                productList.add(product);
                viewProduct.addFoodOk();
                resul = true;
            }else{
                viewProduct.addFoodError();
            }

        }
        return resul;

    }

    public boolean addMeeting(Integer id, String name, double price, LocalDate expiration, int max_people) {
        boolean resul = false;
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twelve = now.plusHours(12);
        if (expiration.isBefore(ChronoLocalDate.from(twelve))) {
            if (max_people <= MAX_PEOPLE) {
                double newPrice = max_people*price;
                Product product = new Product(id, name, newPrice, expiration, max_people);
                product.setProductType(ProductType.MEETING);
                viewProduct.printProductMeeting(product);
                productList.add(product);
                viewProduct.addMeetingOk();
                resul = true;
            }else{
                viewProduct.addMeetingError();
            }

        }
        return resul;
    }

    public boolean removeProduct(int id) {
        boolean result = false;
        for (int i = 0; i < numProducts; i++) {
            if (productList.get(i).getId() == id) {
                viewProduct.printProduct(productList.get(i));
                productList.remove(productList.get(i));
                numProducts--;
                result = true;
                viewProduct.removeOK();

            }
        }

        return result;
    }

    public boolean updateProduct(int id, String objetive, String newValue) {
        boolean result = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                switch (objetive) {
                    case "NAME":
                        productList.get(i).setName(newValue);
                        break;
                    case "PRICE":
                        productList.get(i).setPrice(Double.parseDouble(newValue));
                        break;
                    case "CATEGORY":
                        productList.get(i).setCategory(Category.valueOf(newValue.toUpperCase()));
                        break;
                }
                result = true;
                viewProduct.printProduct(productList.get(i));
                viewProduct.updateOk();
            }
        }
        return result;
    }

    public void list() {
        viewProduct.listProduct(productList);
        viewProduct.listOK();
    }

}
