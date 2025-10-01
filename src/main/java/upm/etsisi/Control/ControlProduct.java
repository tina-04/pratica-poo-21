package upm.etsisi.Control;

import upm.etsisi.Model.Product;
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

    public boolean existProduct(String id) {
        boolean exist = false;
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i) != null) {
                if (productList.get(i).getId().equals(id)) {
                    exist = true;
                }
            }
        }

        return exist;
    }

    public Product addId(String id, String name, String category, double price) {


        return null;
    }
    public Product removeProduct(String id) {
        boolean exist = false;
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).equals(id)){
                productList.remove(productList.get(i));
                numProducts--;
                exist = true;
            }
        }

        return null;
    }


    public String list(){
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 0; i < productList.size(); i++){
            if(productList.get(i) != null){
                stringBuilder.append(productList.get(i).getId()).append(ViewUtility.comma).append(productList.get(i).getName())
                        .append(productList.get(i).getCategory()).append(productList.get(i).getPrice());
            }
        }
        return stringBuilder.toString();
    }


}
