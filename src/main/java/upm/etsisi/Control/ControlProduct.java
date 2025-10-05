package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewProduct;
import upm.etsisi.View.ViewUtility;

import java.util.ArrayList;
import java.util.List;

//import static jdk.internal.org.jline.utils.AttributedStringBuilder.append;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private ViewProduct viewProduct;

    public ControlProduct(int size) {
        this.productList = new ArrayList<>(size);
        this.numProducts = 0;
    }

    public boolean existProduct(int id) { // TODO: Discutir si cambiar por un searchProduct que usar en los comandos
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

    public boolean addProduct(Product product) {
        boolean result = false;
        if(productList.size() < MAX_PRODUCT){
            if(!existProduct(product.getId())){
                if(Utility.leerNum(product.getId())){
                    productList.add(product);
                    numProducts++;
                    result = true;
                }

            }
        }

        return result;
    }

    public boolean removeProduct(int id) {
        boolean result = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id) {
                productList.remove(productList.get(i));
                numProducts--;
                result = true;
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
                    case "CATEGORY": // TODO: Preguntar si hace falta esta, se me hace poco lógico cambiar la categoría de un producto y no hay ejemplos que lo usen
                        productList.get(i).setCategory(Category.valueOf(newValue.toUpperCase()));
                        break; // Yo revisaría este caso con alguna prueba por si acaso
                }
                result = true;
            }
        }
        return result;
    }


    public void list() {
        viewProduct.listProduct(productList);
    }
}
