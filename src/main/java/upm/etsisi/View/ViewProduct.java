package upm.etsisi.View;

import upm.etsisi.Model.Product;

import java.util.List;

public class ViewProduct {
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public String printPoroducto(Product product) {
        StringBuilder sb = new StringBuilder();
        if(product != null){
            messageOutput("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+
                    "', category : " + product.getCategory()+ " ，price : " + product.getPrice() + "}");
        }
        return sb.toString();
    }

    public void listProduct(List<Product> productList){
        for(Product product : productList){
            if(product != null){
                messageOutput("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                        product.getCategory()+ " ，price : " + product.getPrice() + " }");
            }
        }

    }
}
