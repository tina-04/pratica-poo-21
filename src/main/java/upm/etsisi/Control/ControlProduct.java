package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewProduct;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private final int MAX_PEOPLE = 100;
    private final int MAX_TEXT =100;
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
        for(int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                if (productList.get(i).getId() == id  ){
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


    public boolean addProduct(Product product) {
        boolean result = false;
        if(productList.size() < MAX_PRODUCT){
            if(!existProduct(product.getId())){
                if(product.getId() > 0 && product.getId() < MAX_PRODUCT){
                    productList.add(product);
                    numProducts++;
                    result = true;
                    viewProduct.printProduct(product);
                    viewProduct.createOK();
                }

            }
        }

        return result;
    }
    public boolean addProduct2(Integer id, String name, Category category, double price, Integer max_people) {//TODO
        boolean result = false;
        if(max_people==null){
            if(productList.size() < MAX_PRODUCT){
                if(!existProduct(Integer.valueOf(id))) {
                    Product product = new Product(id, name, category, price);
                    productList.add(product);
                    numProducts++;
                    result = true;
                    viewProduct.printProduct(product);
                    viewProduct.createOK();
                }
            }
        } else{
                if(productList.size() < MAX_PRODUCT) {
                    if (!existProduct(Integer.valueOf(id))) {
                        Product product = new Product(id, name, category, price, max_people);
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

    public void addFood(String id, String name, double price, LocalDateTime expiration, String max_people){//TODO
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twelve = now.minusHours(12);
        if(expiration.isBefore(twelve)){
            if(max_people.length() < MAX_PEOPLE){
               // Product product = new Product(id,name,price,max_people);
                //productList.add(product);
                viewProduct.addFoodOk();
            }else{
                throw new IllegalArgumentException(viewProduct.exceptionArguments);
            }
        }


    }
    public void addMeeting(String id, String name, double price,LocalDateTime expiration, String max_people){//TODO
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime twelve = now.minusDays(3);
        if(expiration.isBefore(twelve)){
            if(max_people.length() < MAX_PEOPLE){
                //Product product = new Product(id,name,price,max_people);
               // productList.add(product);
                viewProduct.addMeetingOk();
            }else{
                throw new IllegalArgumentException(viewProduct.exceptionArguments);
            }
        }

    }

    public boolean removeProduct(int id) {
        boolean result = false;
        for (int i = 0; i < numProducts; i++) {
            if (productList.get(i).getId()==id) {
                viewProduct.printProduct(productList.get(i));
                productList.remove(productList.get(i));
                numProducts--;
                result = true;
                viewProduct.removeOK();

            }
        }

        return result;
    }

    public boolean updateProduct(int id, String objetive,String newValue){
        boolean result = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id) {
                switch(objetive){
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

            }
        }
        return result;
    }

    public void list() {
        viewProduct.listProduct(productList);
        viewProduct.listOK();
    }

}
