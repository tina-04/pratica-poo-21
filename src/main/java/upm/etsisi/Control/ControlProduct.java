package upm.etsisi.Control;

import upm.etsisi.Model.Product;
import upm.etsisi.Utility.Utility;
import upm.etsisi.View.ViewProduct;
import upm.etsisi.View.ViewUtility;

import java.util.ArrayList;
import java.util.List;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;
    private final int MAX_PRODUCT = 200;

    public ControlProduct(int size) {
        this.productList = new ArrayList<>(size);
        this.numProducts = 0;
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

    public boolean addId(Product product) {
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
        boolean resul = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId()==id) {
                productList.remove(productList.get(i));
                numProducts--;
                resul = true;
            }
        }

        return resul;
    }


    public String list() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                stringBuilder.append(ViewUtility.bracketLeft).append(ViewProduct.classProduct).append(ViewUtility.comma).
                        append(ViewProduct.id).append(ViewUtility.colon).append(productList.get(i).getId()).append(ViewUtility.comma).
                        append(ViewProduct.name).append(ViewUtility.colon).append(productList.get(i).getName()).append(ViewUtility.comma).
                        append(ViewProduct.category).append(ViewUtility.colon).append(productList.get(i).getCategory()).append(ViewUtility.comma).
                        append(ViewProduct.price).append(ViewUtility.colon).append(productList.get(i).getPrice()).
                        append(ViewUtility.bracketRight);
            }
        }
        return stringBuilder.toString();
    }


}
