package upm.etsisi.Control;

import upm.etsisi.Model.*;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.ProductType;
import upm.etsisi.View.ViewProduct;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class ControlProduct {
    private List<Product> productList;
    private Map<String, ProductsAndService> ps = new HashMap<>();
    private int serviceCounter = 1;
    Map<String, ProductsAndService> productsList = new HashMap<>();

    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private final int MAX_PEOPLE = 100;
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
        String idP  = String.valueOf(id);
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                if (productsList.get(idP)!=null) {
                    exist = true;
                }
            }
        }

        return exist;
    }

    public Product searchProduct(int id) {
        Product product = null;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId().equals(id)) {
                product = productList.get(i);
            }
        }
        return product;
    }

    public boolean addProduct(Integer id, String name, Category category, double price, Integer max_people) {
        boolean result = false;
        if (max_people == null) {
            if (productList.size() < MAX_PRODUCT) {
                if (!existProduct(id)) {
                    BasicProduct product = new BasicProduct(String.valueOf(id), name, category, price);
                    ps.put(product.getId(), product);
                    numProducts++;
                    result = true;
                    viewProduct.printProduct(product);
                    viewProduct.createOK();
                }
            }
        } else {
            if (productList.size() < MAX_PRODUCT) {
                if (!existProduct(id)) {
                    BasicProduct product = new BasicProduct(String.valueOf(id), name, category, price, max_people, null);
                    ps.put(product.getId(), product);
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
        if (validDateFood(expiration)) {
            if (max_people <= MAX_PEOPLE) {
                TimedProduct product = new TimedProduct(String.valueOf(id), name, price, expiration, max_people, ProductType.FOOD);
                viewProduct.printProductFood(product);
                ps.put(product.getId(), product);
                viewProduct.addFoodOk();
                resul = true;
            }else{
                viewProduct.addFoodError();
            }
        }else {
            viewProduct.addFoodError();
        }
        return resul;
    }


    public boolean validDateFood(LocalDate expiration){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime preparation = now.plusDays(3);
        LocalDateTime exp = expiration.atStartOfDay();
        return preparation.isBefore(exp);
    }

    public boolean addMeeting(Integer id, String name, double price, LocalDate expiration, int max_people) {
        boolean resul = false;
        if (validDateMeeting(expiration)) {
            if (max_people <= MAX_PEOPLE) {
                TimedProduct product = new TimedProduct(String.valueOf(id), name, price, expiration, max_people, ProductType.MEETING);
                viewProduct.printProductMeeting(product);
                ps.put(product.getId(), product);
                viewProduct.addMeetingOk();
                resul = true;
            }else{
                viewProduct.addMeetingError();
            }
        }else {
            viewProduct.addMeetingError();
        }
        return resul;
    }

    public boolean validDateMeeting(LocalDate expiration){
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime preparation = now.plusHours(12);
        LocalDateTime exp = expiration.atStartOfDay();
        return preparation.isBefore(exp);
    }

    public boolean removeProduct(int id) {
        boolean result = false;
        for (int i = 0; i < numProducts; i++) {
            if (productList.get(i).getId() == String.valueOf(id)) {
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
            if (productList.get(i).getId() == String.valueOf(id)) {
                Product product = productList.get(i);
                switch (objetive) {
                    case "NAME":
                        product.setName(newValue);
                        break;
                    case "PRICE":
                        product.setPrice(Double.parseDouble(newValue));
                        break;
                    case "CATEGORY":
                        if (product instanceof BasicProduct) {
                            ((BasicProduct) product).setCategory(Category.valueOf(newValue.toUpperCase()));
                        }
                        break;
                }
                result = true;
                viewProduct.printProduct(product);
                viewProduct.updateOk();
            }
        }
        return result;
    }

    public void list() {
        viewProduct.listProduct(productList);
        viewProduct.listOK();
    }
    public void listPS(){
        viewProduct.printAll(ps.values());
        viewProduct.listOK();
    }
    public boolean addService(LocalDate expiration, Category category) {
        boolean result = true;

        String id = serviceCounter++ + "S";
        ProductService service = new ProductService(category, expiration);
        service.setId(id);
        ps.put(id, service);
        viewProduct.printProductService(service);
        viewProduct.createOK();


        return result;
    }
    public Product getProduct(String id) {
        ProductsAndService item = ps.get(id);
        return (item instanceof Product) ? (Product) item : null;
    }
    public ProductService getService(String id) {
        ProductsAndService item = ps.get(id);
        return (item instanceof ProductService) ? (ProductService) item : null;
    }
    public boolean exists(String id) {
        return ps.containsKey(id);
    }



}