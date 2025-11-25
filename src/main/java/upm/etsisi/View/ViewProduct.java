package upm.etsisi.View;

import upm.etsisi.Model.Product;

import java.awt.event.ActionListener;
import java.util.List;

public class ViewProduct implements View{
    public String exceptionArguments = "The number of people is maximum";
    public void messageOutput(String output) {
        System.out.println(output);
    }
    public void printProduct(Product product) {
        if(product != null){
            messageOutput("{class:Product, id:" + product.getId()+ ", name: '" + product.getName()+
                    "', category:" + product.getCategory()+ ", price:" + product.getPrice() + "}");
        }

    }
    public void printProductP(Product product) {
        if(product != null){
            messageOutput("{class:Product, id:" + product.getId()+ ", name: '" + product.getName()+
                    "', category:" + product.getCategory()+ ", price:" + product.getPrice() +" ,maxpersonal:"+ product.getMaxPersonal()+ "}");
        }

    }
    public void printProductFood(Product product) {
        if(product != null){
            messageOutput("{class:Food, id:" + product.getId()+ ", name: '" + product.getName()+
                   ", price:" + product.getPrice() +  ", date of Event:"+product.getExpiration()+
                    ",max people allowed:"+product.getMaxPersonal()+"}");
        }

    }
    public void printProductMeeting(Product product) {
        if(product != null){
            messageOutput("{class:Meeting, id:" + product.getId()+ ", name: '" + product.getName()+
                    ", price:" + product.getPrice() +  ", date of Event:"+product.getExpiration()+
                    ",max people allowed:"+product.getMaxPersonal()+"}");
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
    public void addFoodError(){
        messageOutput("Error processing ->prod addFood ->Error adding product");
    }
    public void addMeetingError(){
        messageOutput("Error processing ->prod addMeeting ->Error adding product");
    }
    public void createOK(){
        messageOutput("prod add: ok");
    }

    public void removeOK(){
        messageOutput("prod remove: ok");
    }
    public void listOK(){
        messageOutput("prod list: ok");
    }
    public void addFoodOk(){
        messageOutput("prod addFood: ok");
    }
    public void addMeetingOk(){
        messageOutput("prod addMeeting: ok");
    }
}
