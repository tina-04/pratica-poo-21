package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Category;
import upm.etsisi.View.ViewProduct;

import java.util.ArrayList;
import java.util.List;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;
    private final int MAX_PRODUCT = 200;
    private ViewProduct viewProduct;

    public ControlProduct(int size) {
        this.productList = new ArrayList<>(size);
        this.numProducts = 0;
        this.viewProduct = new ViewProduct();
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

    public Product getProduct(int id) { // TODO: Para usarlo en APP y ticket add, no sé si coge bien por el id o lo toma como index
        return productList.get(id);
    }

    public boolean addProduct(Product product) {
        boolean result = false;
        if(productList.size() < MAX_PRODUCT){
            if(!existProduct(product.getId())){
                if(product.getId() > 0 && product.getId() < MAX_PRODUCT){
                    productList.add(product);
                    numProducts++;
                    result = true;
                    printProduct(product);
                }

            }
        }

        return result;
    }

    public boolean removeProduct(int id) {
        boolean result = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id) {
                printProduct(productList.get(i));
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
                printProduct(productList.get(i));

            }
        }
        return result;
    }

    public void list() {
        viewProduct.listProduct(productList);
    }
    public void printProduct(Product product) {
        viewProduct.printPoroducto(product);
    }
}
