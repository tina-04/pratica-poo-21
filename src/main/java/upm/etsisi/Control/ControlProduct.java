package upm.etsisi.Control;

import upm.etsisi.Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ControlProduct {
    private List<Product> productList;
    private int numProducts;

    public ControlProduct(int size) {
        this.productList = new ArrayList<>(size);
        this.numProducts = 0;
    }

}
