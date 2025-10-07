package upm.etsisi.View;

import upm.etsisi.Model.Product;

import java.util.List;

public class ViewProduct {
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public static final String  classProduct = " class:Product ";
    public static  final String name = " name";
    public static  final String price = " price";
    public static  final String category = " category";
    public static  final String id = " id";
    public static  final String comma = " ,";
    public static  final String colon = " : ";
    public static  final String classP = " class:Product";
    public static  final String brackeatsLeft = "{";
    public static  final String bracketsRight = "}";
    public String printPoroducto(Product product) {
        StringBuilder sb = new StringBuilder();
        if(product != null){
            sb.append("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                    product.getCategory()+ "price : " + product.getPrice());
        }
        return sb.toString();
    }

    public void listProduct(List<Product> productList){
        for(Product product : productList){
            if(product != null){
                messageOutput("{class:Product，id: " + product.getId()+ "name : '" + product.getName()+ "', category : " +
                        product.getCategory()+ "price : " + product.getPrice());
            }
        }

    }
}
