package upm.etsisi.View;

import upm.etsisi.Model.Product;

import java.util.List;

public class ViewProduct {
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printProduct(Product product) {
        if(product != null){
            messageOutput("{class:Product, id:" + product.getId()+ ", name: '" + product.getName()+
                    "', category:" + product.getCategory()+ ", price:" + product.getPrice() + "}");
        }

    }

    public void listProduct(List<Product> productList){
        messageOutput("Catalog:");
        for(Product product : productList){
            if(product != null) {
                printProduct(product);
            }

        }

    }
}
